package fr.lteconsulting.hexa.databinding.annotation.processor;


import fr.lteconsulting.hexa.databinding.annotation.processor.modules.ProcessorModule;

import java.util.ArrayList;
import java.util.List;

public class ProcessorModuleRegistry {

    private static List<ProcessorModule> processorModules = new ArrayList<>();

    public static void register(ProcessorModule module) {
        processorModules.add(module);
    }

    protected static List<ProcessorModule> getProcessorModules() {
        return processorModules;
    }
}
