package fr.lteconsulting.hexa.databinding.gwt.annotation.processor;

import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;
import fr.lteconsulting.hexa.databinding.annotation.processor.ObservableAnnotationProcessor;
import fr.lteconsulting.hexa.databinding.annotation.processor.Template;
import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.Annotation;

@SupportedAnnotationTypes({
	"fr.lteconsulting.hexa.databinding.gwt.annotation.Observable",
	"fr.lteconsulting.hexa.databinding.annotation.ObservableGwt"
})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class GwtObservableAnnotationProcessor extends ObservableAnnotationProcessor {
	private final static String TEMPLATE_CLASS = "fr/lteconsulting/hexa/databinding/gwt/annotation/processor/TemplateClass.txt";

	@Override
	protected String generateExtraImports(ProcInfo procInfo) {
		String extraImports = super.generateExtraImports(procInfo);
		if(!extraImports.contains("fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle")) {
			extraImports += "import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;\n";
		}
		if(!extraImports.contains("fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses")) {
			extraImports += "import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;\n";
		}
		return extraImports;
	}

	@Override
	protected StringBuilder generateClassEntry(ProcInfo procInfo) {
		return super.generateClassEntry(procInfo)
			.append(Template.fromResource(TEMPLATE_CLASS, BEGIN_INDEX).toString());
	}

	@Override
	protected int getInheritDepth(Annotation annotation) {
		if(annotation instanceof Observable) {
			Observable observable = ((Observable) annotation);
			int depth = observable.inheritDepth();
			return observable.inherit() ? depth : (depth != Observable.INHERIT_MAX ? depth : 0);
		}
		// Duplication for legacy annotation
		else if(annotation instanceof ObservableGwt) {
			ObservableGwt observable = ((ObservableGwt) annotation);
			int depth = observable.inheritDepth();
			return observable.inherit() ? depth : (depth != ObservableGwt.INHERIT_MAX ? depth : 0);
		}
		return -1;
	}

	@Override
	protected boolean canUseCopyConstructor(Annotation annotation) {
		if(annotation instanceof Observable) {
			return ((Observable)annotation).copyConstructor();
		}
		// Duplication for legacy annotation
		else if(annotation instanceof ObservableGwt) {
			return ((ObservableGwt)annotation).copyConstructor();
		}
		return false;
	}
}
