package fr.lteconsulting.hexacssmaven;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Process a CSS file, rename classes according to the mapping file and prune
 * all unused classes
 */
@Mojo( name = "process", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class HexaCssProcessMojo extends AbstractMojo
{
	@Parameter( defaultValue = "${project.build.directory}/${project.build.finalName}" )
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
		getLog().info( "HexaCss - Process Css files" );
		getLog().info( "===========================" );
		getLog().info( "Source dir: " + sourceDirectory.getAbsolutePath() );
		getLog().info( "Mapping file: " + mappingFile.getAbsolutePath() );
		getLog().info( "Output dir: " + outputDirectory.getAbsolutePath() );

		checkFileExists( sourceDirectory );
		checkFileExists( mappingFile );
		checkFileExists( outputDirectory );

		if( includes != null )
		{
			for( String include : includes )
			{
				try
				{
					getLog().info( "Processing file: " + include );
					CssMapper.processFile( sourceDirectory.getAbsolutePath() + "/" + include, mappingFile.getAbsolutePath(), outputDirectory.getAbsolutePath() + "/" + include, pruneClasses, getLog() );
				}
				catch( IOException e )
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
}
