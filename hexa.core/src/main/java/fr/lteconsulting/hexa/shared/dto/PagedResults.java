package fr.lteconsulting.hexa.shared.dto;

import java.io.Serializable;
import java.util.List;

public class PagedResults<T> implements Serializable
{
	private static final long serialVersionUID = 6370871510640834805L;

	public List<T> results;
	public int offset;
	public int totalNumberResults;
}
