package fr.lteconsulting.hexa.databinding.annotation.processor.modules;

import fr.lteconsulting.hexa.databinding.annotation.processor.BaseAnnotationProcessor.ProcInfo;

import java.util.List;

public interface ProcessorModule {

    List<String> getImports(ProcInfo procInfo);

    String getClassEntry(ProcInfo procInfo);
}
