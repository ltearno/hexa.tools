package fr.lteconsulting.hexa.databinding.test;

import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

@Observable
public class SampleDTO2
{
	String tata;
	int toto;
	boolean tutu;
	long l;
	float f;
	byte b;
	
	String[] tatas;
	int[] totos;
	boolean[] tutus;
	long[] ls;
	float fs[];
	byte bs[];
	
	List<Object> os;
	
	HashMap<String, List<ObservableSampleDTO1>> map;

	// self reference
	SampleDTO2 titi2;
	
	SampleDTO1 titi;

	List<SampleDTO1> titis;

	List<SampleDTO1> totos4;

	// does not work, give <any>
	//ObservableSampleDTO3<Integer> toto2;

	// does not work, give List<<any>>
	//List<ObservableSampleDTO3<Integer>> totos2;

	// does not work, give List<<any>>
	//List<ObservableSampleDTO3<Integer>> totos3;
}
