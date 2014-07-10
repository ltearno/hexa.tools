package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category
{
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private int id;

	private String codeEAN;

	private String marque;

	@OneToMany( mappedBy = "category" )
	private List<Article> articles;

	public int getId()
	{
		return id;
	}

	public String getCodeEAN()
	{
		return codeEAN;
	}

	public void setCodeEAN( String codeEAN )
	{
		this.codeEAN = codeEAN;
	}

	public String getMarque()
	{
		return marque;
	}

	public void setMarque( String marque )
	{
		this.marque = marque;
	}

	public List<Article> getArticles()
	{
		return articles;
	}

	public void setArticles( List<Article> articles )
	{
		this.articles = articles;
	}

	@Override
	public String toString()
	{
		return "Category [id=" + id + ", codeEAN=" + codeEAN + ", marque=" + marque + "]";
	}
}


/*
@Entity
public class ArticleEntity implements Serializable
{
	private static final long serialVersionUID = -7411651786435116742L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	@Index( name = "IDX_CODE_SAP" )
	private String codeSAP;
	@Index( name = "IDX_CODE_EAN" )
	private String codeEAN;
	@Index( name = "IDX_LIBELLE_SFA" )
	private String libelleSFA;
	private String contenanceCommerciale;
	private String marque;
	@Index( name = "IDX_LIGNE_COMMERCE_CODE" )
	private String ligneCommerceCode;
	private String ligneCommerceLibelle;
	private String statutADVSAP;
	private String utilisationCommerce;
	private String codeUtilisationCommerce;
	private String hierarchieActivite;
	private boolean isGratuit;
	private int uniteLivraison;
	private int conditionnement;
	private String libelleSAP;
	private String axe;
	private String market;
	private int price;

	// ALTER TABLE `articleentity` ADD `lastUpdate` TIMESTAMP ON UPDATE
	// CURRENT_TIMESTAMP NULL DEFAULT NULL
	// @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Column(columnDefinition = "timestamp ON UPDATE CURRENT_TIMESTAMP")
	@Generated(GenerationTime.ALWAYS)
	private Date lastUpdate;

	public static String SQL_DTO( String variableName )
	{
		String res = "new " + Article.class.getName()
				+ "(XXX.id, XXX.codeSAP, XXX.codeEAN, XXX.libelleSFA, XXX.contenanceCommerciale, XXX.marque, XXX.ligneCommerceCode, "
				+ "XXX.ligneCommerceLibelle, XXX.statutADVSAP, XXX.utilisationCommerce, XXX.codeUtilisationCommerce, XXX.hierarchieActivite, "
				+ "XXX.isGratuit, XXX.uniteLivraison, XXX.conditionnement, XXX.libelleSAP, XXX.axe, XXX.market, XXX.price, XXX.lastUpdate)";
		res = res.replaceAll( "XXX", variableName );

		return res;
	}

	public void copyFrom( Article article )
	{
		setCodeSAP( article.codeSAP );
		setCodeEAN( article.codeEAN );
		setLibelleSFA( article.libelleSFA );
		setContenanceCommerciale( article.contenanceCommerciale );
		setMarque( article.marque );
		setLigneCommerceCode( article.ligneCommerceCode );
		setLigneCommerceLibelle( ligneCommerceLibelle );
		setStatutADVSAP( article.statutADVSAP );
		setUtilisationCommerce( article.utilisationCommerce );
		setCodeUtilisationCommerce( article.codeUtilisationCommerce );
		setHierarchieActivite( article.hierarchieActivite );
		setGratuit( article.isGratuit );
		setUniteLivraison( article.uniteLivraison );
		setConditionnement( article.conditionnement );
		setLibelleSAP( article.libelleSAP );
		setAxe( article.axe );
		setMarket( article.market );
		setPrice( article.price );
	}

	public Article toDto()
	{
		return copyTo( null );
	}

	public Article copyTo( Article article )
	{
		if( article == null )
			article = new Article();

		article.id = getId();

		article.codeSAP = getCodeSAP();
		article.codeEAN = getCodeEAN();
		article.libelleSFA = getLibelleSFA();
		article.contenanceCommerciale = getContenanceCommerciale();
		article.marque = getMarque();
		article.ligneCommerceCode = getLigneCommerceCode();
		article.ligneCommerceLibelle = getLigneCommerceLibelle();
		article.statutADVSAP = getStatutADVSAP();
		article.utilisationCommerce = getUtilisationCommerce();
		article.codeUtilisationCommerce = getCodeUtilisationCommerce();
		article.hierarchieActivite = getHierarchieActivite();
		article.isGratuit = isGratuit();
		article.uniteLivraison = getUniteLivraison();
		article.conditionnement = getConditionnement();
		article.libelleSAP = getLibelleSAP();
		article.axe = getAxe();
		article.market = getMarket();
		article.price = getPrice();
		article.lastUpdate = getLastUpdate();

		return article;
	}

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public String getCodeSAP()
	{
		return codeSAP;
	}

	public void setCodeSAP( String codeSAP )
	{
		this.codeSAP = codeSAP;
	}

	public String getCodeEAN()
	{
		return codeEAN;
	}

	public void setCodeEAN( String codeEAN )
	{
		this.codeEAN = codeEAN;
	}

	public String getLibelleSFA()
	{
		return libelleSFA;
	}

	public void setLibelleSFA( String libelleSFA )
	{
		this.libelleSFA = libelleSFA;
	}

	public String getContenanceCommerciale()
	{
		return contenanceCommerciale;
	}

	public void setContenanceCommerciale( String contenanceCommerciale )
	{
		this.contenanceCommerciale = contenanceCommerciale;
	}

	public String getMarque()
	{
		return marque;
	}

	public void setMarque( String marque )
	{
		this.marque = marque;
	}

	public String getLigneCommerceCode()
	{
		return ligneCommerceCode;
	}

	public void setLigneCommerceCode( String ligneCommerceCode )
	{
		this.ligneCommerceCode = ligneCommerceCode;
	}

	public String getLigneCommerceLibelle()
	{
		return ligneCommerceLibelle;
	}

	public void setLigneCommerceLibelle( String ligneCommerceLibelle )
	{
		this.ligneCommerceLibelle = ligneCommerceLibelle;
	}

	public String getStatutADVSAP()
	{
		return statutADVSAP;
	}

	public void setStatutADVSAP( String statutADVSAP )
	{
		this.statutADVSAP = statutADVSAP;
	}

	public String getUtilisationCommerce()
	{
		return utilisationCommerce;
	}

	public void setUtilisationCommerce( String utilisationCommerce )
	{
		this.utilisationCommerce = utilisationCommerce;
	}

	public String getCodeUtilisationCommerce()
	{
		return codeUtilisationCommerce;
	}

	public void setCodeUtilisationCommerce( String codeUtilisationCommerce )
	{
		this.codeUtilisationCommerce = codeUtilisationCommerce;
	}

	public String getHierarchieActivite()
	{
		return hierarchieActivite;
	}

	public void setHierarchieActivite( String hierarchieActivite )
	{
		this.hierarchieActivite = hierarchieActivite;
	}

	public boolean isGratuit()
	{
		return isGratuit;
	}

	public void setGratuit( boolean isGratuit )
	{
		this.isGratuit = isGratuit;
	}

	public int getUniteLivraison()
	{
		return uniteLivraison;
	}

	public void setUniteLivraison( int uniteLivraison )
	{
		this.uniteLivraison = uniteLivraison;
	}

	public int getConditionnement()
	{
		return conditionnement;
	}

	public void setConditionnement( int conditionnement )
	{
		this.conditionnement = conditionnement;
	}

	public String getLibelleSAP()
	{
		return libelleSAP;
	}

	public void setLibelleSAP( String libelleSAP )
	{
		this.libelleSAP = libelleSAP;
	}

	public String getAxe()
	{
		return axe;
	}

	public void setAxe( String axe )
	{
		this.axe = axe;
	}

	public String getMarket()
	{
		return market;
	}

	public void setMarket( String market )
	{
		this.market = market;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice( int price )
	{
		this.price = price;
	}

	public Date getLastUpdate()
	{
		return lastUpdate;
	}

	public void setLastUpdate( Date lastUpdate )
	{
		this.lastUpdate = lastUpdate;
	}
}
*/