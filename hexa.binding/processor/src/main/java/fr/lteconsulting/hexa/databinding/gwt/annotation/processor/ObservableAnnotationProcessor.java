package fr.lteconsulting.hexa.databinding.gwt.annotation.processor;

import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;
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
public class ObservableAnnotationProcessor extends fr.lteconsulting.hexa.databinding.annotation.processor.ObservableAnnotationProcessor
{
	private final static String TEMPLATE_CLASS = "fr/lteconsulting/hexa/databinding/gwt/annotation/processor/TemplateClass.txt";

	@Override
	protected String generateExtraImports(ProcInfo procInfo) {
		StringBuilder sb = new StringBuilder();
		sb.append("import com.google.gwt.core.client.GWT;\n");
		sb.append("import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;\n");
		sb.append("import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;\n");
		return sb.toString();
	}

	@Override
	protected String generateClassEntry(ProcInfo procInfo) {
		return Template.fromResource(TEMPLATE_CLASS, BEGIN_INDEX).toString();
	}

	@Override
	protected int getInheritDepth(Annotation annotation) {
		if(annotation instanceof Observable) {
			Observable observable = (Observable)annotation;
			int depth = observable.inherit() ? 1 : 0;
			return observable.inheritDepth() > depth ? observable.inheritDepth() : depth;
		}
		// Duplication for legacy annotation
		else if(annotation instanceof ObservableGwt) {
			ObservableGwt observable = (ObservableGwt)annotation;
			int depth = observable.inherit() ? 1 : 0;
			return observable.inheritDepth() > depth ? observable.inheritDepth() : depth;
		}
		return -1;
	}

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
