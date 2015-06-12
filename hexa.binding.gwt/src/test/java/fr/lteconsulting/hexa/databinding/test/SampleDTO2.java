package fr.lteconsulting.hexa.databinding.test;

import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;

@ObservableGwt
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
	
	HashMap<String, List<SampleDTO1>> map;

	// self reference
	SampleDTO2 titi2;
	
	SampleDTO1 titi;

	// does not work, give <any>
	// ObservableToto<Integer> toto2;

	List<SampleDTO1> titis;

	// does not work, give List<<any>>
	//List<ObservableToto<Integer>> totos2;

	// does not work, give List<<any>>
	//List<ObservableTiti<Integer>> totos3;

	List<SampleDTO1> totos4;
}
