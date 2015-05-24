package fr.lteconsulting.hexacssmaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.classworlds.ClassRealm;
import org.codehaus.classworlds.ClassWorld;
import org.codehaus.classworlds.DuplicateRealmException;

/**
 * Process a CSS file, rename classes according to the mapping file and prune
 * all unused classes
 */
@Mojo( name = "process", defaultPhase = LifecyclePhase.PROCESS_SOURCES, requiresDependencyResolution=ResolutionScope.COMPILE )
public class HexaCssProcessMojo extends AbstractMojo
{
	private static final String CLASSPATH_MAKER = "classpath:";

	@Parameter( defaultValue = "${project.build.directory}" )
	private File sourceDirectory;

	@Parameter( defaultValue = "${project.build.directory}/${project.build.finalName}" )
	private File outputDirectory;

	@Parameter
	private List<String> includes;

	@Parameter( defaultValue = "${project}" )
	private org.apache.maven.project.MavenProject mavenProject;

	@Parameter( required = true )
	private File mappingFile;

	@Parameter( defaultValue = "true" )
	private Boolean pruneClasses;

	// Only used internally to get a reference to the current dependencies
	@Parameter( defaultValue = "${project.dependencyArtifacts}", required = true, readonly = true )
	private Set<Artifact> dependencyArtifacts;

	private void checkFileExists( File file ) throws MojoExecutionException
	{
		if( file == null )
			throw new MojoExecutionException( "The file is not specified !" );

		if( !file.exists() )
			throw new MojoExecutionException( "The file '" + file.getAbsolutePath() + "' does not exist !" );
	}

	@Override
	public void execute() throws MojoExecutionException
	{
		getLog().info( "Source directory: " + maybeClasspathPath( sourceDirectory.getAbsolutePath() ) );
		getLog().info( "Mapping file: " + mappingFile.getAbsolutePath() );
		getLog().info( "Output directory: " + outputDirectory.getAbsolutePath() );

		checkFileExists( mappingFile );
		try
		{
			Files.createDirectories( outputDirectory.toPath() );
		}
		catch( IOException e1 )
		{
			e1.printStackTrace();
			throw new MojoExecutionException( "Cannot create the directory '" + outputDirectory.getAbsolutePath() + "' !" );
		}
		checkFileExists( outputDirectory );

		if( includes != null )
		{
			for( String include : includes )
			{
				try
				{
					getLog().info( "Processing file: " + include );

					String sourcePath = maybeClasspathPath( sourceDirectory.getAbsolutePath() + "/" + include );

					String sourceFile = loadFile( sourcePath );
					if( sourceFile == null )
					{
						getLog().error( "Cannot load file : " + sourcePath + ". Aborting." );
						throw new MojoExecutionException( "Canoot load file : " + sourcePath );
					}

					CssMapper.processFile( sourceFile, mappingFile.getAbsolutePath(), outputDirectory.getAbsolutePath() + "/" + include, pruneClasses, getLog() );
				}
				catch( IOException | URISyntaxException e )
				{
					getLog().error( "Error when processing the file", e );
				}
			}

			getLog().info( "Done." );
		}
		else
		{
			getLog().info( "No file specified for processing, nothing to do..." );
		}
	}

	private String loadFile( String sourcePath ) throws URISyntaxException, MojoExecutionException
	{
		final String sourceContent;

		if( sourcePath.startsWith( CLASSPATH_MAKER ) )
		{
			sourcePath = sourcePath.replaceAll( "\\\\", "/" );
			
			ClassWorld world = new ClassWorld();
			ClassRealm realm;
			try
			{
				realm = world.newRealm( "hexacss", null );
				for( Artifact artifact : dependencyArtifacts )
				{
					getLog().debug( "Analysing Artifact " + artifact.getGroupId() + ":" + artifact.getId() + "-" + artifact.getClassifier() );
					
					if( !artifact.getArtifactHandler().isAddedToClasspath() )
						continue;
					
					File artifactFile = artifact.getFile();
					if(artifactFile==null)
					{
						getLog().error( "Artifact file is null, maybe dependency resolution has not taken place ! try just 'mvn install' on the whole project" );
					}
					else
					{
						URI uri = artifactFile.toURI();
						realm.addConstituent( uri.toURL() );
						getLog().debug( "Added to realm : " + uri );
					}
				}
			}
			catch( DuplicateRealmException e )
			{
				throw new MojoExecutionException( "Duplicate realm when reading resource " + sourcePath, e );
			}
			catch( MalformedURLException e )
			{
				throw new MojoExecutionException( "Malformed URL for resource " + sourcePath, e );
			}

			String resourceName = sourcePath.substring( CLASSPATH_MAKER.length() );
			
			getLog().info( "Processing a classpath resource : " + resourceName );
			
			try(InputStream is = realm.getResourceAsStream( resourceName );)
			{
				if(is==null)
					throw new MojoExecutionException( "Cannot read resource stream " + resourceName );
				
				sourceContent = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
			}
			catch( IOException e )
			{
				throw new MojoExecutionException( "Cannot read resource " + resourceName, e );
			}
		}
		else
		{
			File file = new File(sourcePath);
			checkFileExists( file );
			
			try
			{
				sourceContent = readFile( file );
			}
			catch( IOException e )
			{
				throw new MojoExecutionException( "Cannot read file " + sourcePath, e );
			}
		}

		return sourceContent;
	}
	
	private static String readFile( File file ) throws IOException, UnsupportedEncodingException, FileNotFoundException
	{
		String str = "";
		try( FileInputStream fis = new FileInputStream( file ) )
		{
			byte[] data = new byte[(int) file.length()];
			fis.read( data );
			str = new String( data, "UTF-8" );
		}
		return str;
	}

	private String maybeClasspathPath( String path )
	{
		int i = path.indexOf( CLASSPATH_MAKER );
		if( i >= 0 )
			path = path.substring( i );

		return path;
	}
}
