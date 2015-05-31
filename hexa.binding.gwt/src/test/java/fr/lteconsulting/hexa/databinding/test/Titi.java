package fr.lteconsulting.hexa.databinding.test;

import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;

@ObservableGwt
public class Titi<T extends Number>
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
	
	HashMap<String, List<ObservableTiti<Integer>>> map;
}
