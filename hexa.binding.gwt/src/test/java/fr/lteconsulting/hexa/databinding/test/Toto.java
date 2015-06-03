package fr.lteconsulting.hexa.databinding.test;

import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;

@ObservableGwt
public class Toto<T extends Number>
{
	T data;

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
	
	HashMap<String, List<Titi<Integer>>> map;

	ObservableTiti titi;

	// does not work, give <any>
	//ObservableToto<Integer> toto2;

	List<ObservableTiti> titis;

	// does not work, give List<<any>>
	//List<ObservableToto<Integer>> totos2;

	// does not work, give List<<any>>
	//List<ObservableTiti<Integer>> totos3;

	List<ObservableToto> totos4;
}
