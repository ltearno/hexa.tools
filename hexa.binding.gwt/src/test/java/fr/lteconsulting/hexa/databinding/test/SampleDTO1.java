package fr.lteconsulting.hexa.databinding.test;

import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;

@Observable
public class SampleDTO1
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

	SampleDTO1 titi;
}
