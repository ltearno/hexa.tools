package fr.lteconsulting.hexa.databinding.gwt.annotation.processor;

import fr.lteconsulting.hexa.databinding.annotation.processor.Template;
import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

import static fr.lteconsulting.hexa.databinding.annotation.processor.Tags.*;

@SupportedAnnotationTypes({
	"fr.lteconsulting.hexa.databinding.gwt.annotation.Observable",
	"fr.lteconsulting.hexa.databinding.annotation.ObservableGwt"
})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ObservableAnnotationProcessor extends fr.lteconsulting.hexa.databinding.annotation.processor.ObservableAnnotationProcessor
{
	private final static String TEMPLATE_CLASS = "fr/lteconsulting/hexa/databinding/gwt/annotation/processor/TemplateClass.txt";

	private final static int PRE_CONSTRUCTOR_INDEX = 0;

	protected String[] getExtraImports() {
		return new String[] {
			"import com.google.gwt.core.client.GWT;",
			"import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;",
			"import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;"
		};
	}

	@Override
	protected String getPreConstructor(String pkgName, String targetTypeName, TypeElement sourceType) {
		return Template.fromResource(TEMPLATE_CLASS, PRE_CONSTRUCTOR_INDEX).toString()
			.replace(TARGET_CLASS_NAME, targetTypeName);
	}

	protected int inheritFromSuperClassDepth(Annotation annotation) {
		if(annotation instanceof Observable) {
			Observable observable = (Observable)annotation;
			int depth = observable.inherit() ? 1 : 0;
			return observable.inheritDepth() > depth ? observable.inheritDepth() : depth;
		}
		return -1;
	}
}
