package fr.lteconsulting.hexa.client.ui.resources;

public interface Messages extends com.google.gwt.i18n.client.Messages
{
	/*
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 * 
	 * @DefaultMessage("") String xxx();
	 */

	@DefaultMessage( "Mode en ligne disponible" )
	String appState_onlineModeAvailable();

	@DefaultMessage( "-> En ligne" )
	String goingOnline();

	@DefaultMessage( "En ligne" )
	String online();

	@DefaultMessage( "-> Hors-ligne" )
	String goingOffline();

	@DefaultMessage( "Hors-ligne" )
	String offline();

	@DefaultMessage( "Devoxx 2013 - Palo IT - Demo" )
	String applicationTitle();

	@DefaultMessage( "Authentifiez-vous s''il vous plait" )
	String loginPanel_loginTitle();

	@DefaultMessage( "Oui" )
	String yes();

	@DefaultMessage( "Non" )
	String no();

	@DefaultMessage( "Mot de passe" )
	String loginPanel_lblPassword();

	@DefaultMessage( "Login" )
	String loginPanel_lblLogin();

	@DefaultMessage( "Se connecter" )
	String connect();

	@DefaultMessage( "Clients" )
	String client_menu_title();

	@DefaultMessage( "Chercher" )
	String search_Menu();

	@DefaultMessage( "Dernier client" )
	String client_menu_lastClient();

	@DefaultMessage( "Catalogues" )
	String catalogue_menu_title();

	@DefaultMessage( "Tables de référence" )
	String catalogue_menu_tablereferences();

	@DefaultMessage( "Créer" )
	String create_Menu();

	@DefaultMessage( "Commandes" )
	String order_menu_title();

	@DefaultMessage( "Créer une commande" )
	String order_menu_createOrder();

	@DefaultMessage( "Administration" )
	String administration_menu();

	@DefaultMessage( "Utilistauers" )
	String user_menu();

	@DefaultMessage( "Secteurs" )
	String secteur_Menu();

	@DefaultMessage( "Synchronisation" )
	String sychronization_menu_title();

	@DefaultMessage( "Synchroniser" )
	String sychronize_Menu();

	@DefaultMessage( "Connexion" )
	String connection_menu_title();

	@DefaultMessage( "Se déconnecter" )
	String logout_Menu();

	@DefaultMessage( "Une erreur s''est produite !" )
	String anErrorOccurred();

	@DefaultMessage( "Examiner les détails" )
	String seeDetail();

	@DefaultMessage( "Envoyer l''erreur" )
	String btnSendError();

	@DefaultMessage( "Erreur !" )
	String error();

	@DefaultMessage( "Avertissement !" )
	String warningtitle();

	@DefaultMessage( "Le mode hors-ligne est désactivé" )
	String offlineDisableMessage();

	@DefaultMessage( "L''application est à jour en version {0}." )
	String applicationIsUpdated( String applicationVersion );

	@DefaultMessage( "Espace utilisé" )
	String localStroage_toolTipUsedSpace();

	@DefaultMessage( "Espace de stockage local indisponible !" )
	String localStroage_toolTipIndisponible();

	@DefaultMessage( "Utilisateur Inconnu" )
	String unknownUserName();

	@DefaultMessage( "Détails" )
	String details();

	@DefaultMessage( "Enregistrer" )
	String save();

	@DefaultMessage( "Nouveau" )
	String newRecord();

	@DefaultMessage( "Détruire" )
	String deleteRecord();

	@DefaultMessage( "Actualiser" )
	String refresh();

	@DefaultMessage( "Pas d''enregistrement." )
	String noResultInGrid();

	@DefaultMessage( "Recherche des catalogues" )
	String cataloguesearch_menu_title();

	@DefaultMessage( "Edition du catalogue {0}" )
	String editionCatalogueHeaderText( int id );

	@DefaultMessage( "Créer" )
	String btnCreate();

	@DefaultMessage( "Enregistrer" )
	String btnSave();

	@DefaultMessage( "Nouveau catalogue" )
	String editionCatalogueHeaderTextNothingEdited();

	@DefaultMessage( "Libellé long" )
	String searchLibelleLong();

	@DefaultMessage( "Début de validité" )
	String searchDebutValidite();

	@DefaultMessage( "Fin de validité" )
	String searchFinValidite();

	@DefaultMessage( "Remarque" )
	String remarque();

	@DefaultMessage( "Date limite de commande" )
	String dateLimiteCommande();

	@DefaultMessage( "Date de livraison" )
	String dateLivraison();

	@DefaultMessage( "Quotas activés" )
	String quotaActif();

	@DefaultMessage( "Blocage livraison" )
	String blocageLivraison();

	@DefaultMessage( "Tournée" )
	String tournee();

	@DefaultMessage( "Prorogation autorisée" )
	String prorogationAutorisee();

	@DefaultMessage( "Autres articles autorisés" )
	String autresArticlesAutorises();

	@DefaultMessage( "Remise max." )
	String remiseMax();

	@DefaultMessage( "Remise autorisée" )
	String remiseAutorisee();

	@DefaultMessage( "Actif" )
	String actif();

	@DefaultMessage( "Terrain" )
	String terrain();

	@DefaultMessage( "Central" )
	String central();

	@DefaultMessage( "Les deux" )
	String centralTerrain();

	@DefaultMessage( "En dehors des bornes autorisée" )
	String remiseOutOfRangeError();

	@DefaultMessage( "Valider" )
	String btnValid();

	@DefaultMessage( "Exporter" )
	String buttonExport();

	@DefaultMessage( "Détruire" )
	String btnDelete();

	@DefaultMessage( "Annuler" )
	String btnCancel();

	@DefaultMessage( "Le début doit être avant la fin !" )
	String debutValiditeNeedsToBeBeforeFinValidite();

	@DefaultMessage( "La fin doit être à la fin" )
	String finValiditeNeedsToBeAfterFinValidite();

	@DefaultMessage( "Exporter" )
	String editionCatalogueExportButton();

	@DefaultMessage( "Détruire" )
	String editionCatalogueDestroyButton();

	@DefaultMessage( "Actions" )
	String editionCatalogueActionsText();

	@DefaultMessage( "Catalogue non disponible" )
	String unavailableCatalogue();

	@DefaultMessage( "Catalogue non créé" )
	String catalogueNotCreated();

	@DefaultMessage( "Le catalogue ''{0}'' a été créé" )
	String createdCatalogue( String libelleLong );

	@DefaultMessage( "Succès" )
	String success();

	@DefaultMessage( "Veuillez confirmer" )
	String confirmTitle();

	@DefaultMessage( "Enregistrement effectué" )
	String saveSuccess();

	@DefaultMessage( "Destruction de catalogues" )
	String deletedCatalogues();

	@DefaultMessage( "Confirmez-vous effacer ces {0} catalogue(s) ?" )
	String confirmDelete( int i );

	@DefaultMessage( "..." )
	String cannotPasteFromADifferentMarket();

	@DefaultMessage( "Sélectionnez un chapitre" )
	String pleaseSelectAChapter();

	@DefaultMessage( "Article {0} dupliqué, confirmez-vous ?" )
	String articleDuplicateWarning( String string );

	@DefaultMessage( "Nouveau" )
	String btnNew();

	@DefaultMessage( "Copier" )
	String btnCopy();

	@DefaultMessage( "Coller" )
	String btnPaste();

	@DefaultMessage( "Renommer..." )
	String btnRename();

	@DefaultMessage( "Hierarchie du catalogue" )
	String editionCatalogueHierarchy();

	@DefaultMessage( "Déplacer le chapitre" )
	String editionCatalogueMoveChapter();

	@DefaultMessage( "Déplacement impossible" )
	String editionCatalogueMoveImpossible();

	@DefaultMessage( "Déplacer l''item" )
	String editionCatalogueMoveItem();

	@DefaultMessage( "Nouveau chapitre" )
	String editionCatalogueNewChapter();

	@DefaultMessage( "Veuillez sélectionner un chapitre" )
	String editionCataloguePleaseSelectAChapter();

	@DefaultMessage( "Veuillez sélectionner un chapitre et non un item !" )
	String editionCataloguePleaseSelectAChapterNotAnItem();

	@DefaultMessage( "Limité à huit niveaux !" )
	String editionCatalogueLimitedToEightLevels();

	@DefaultMessage( "Saisissez le nom du chapitre" )
	String editionCatalogueEnterChapterName();

	@DefaultMessage( "Défaut" )
	String editionCatalogueNewChapterDefaultName();

	@DefaultMessage( "Impossible" )
	String editionCatalogueNewChapterImpossible();

	@DefaultMessage( "Destruction de catalogue" )
	String editionCatalogueDeleting();

	@DefaultMessage( "Rien n''est sélectionné" )
	String editionCatalogueNothingSelected();

	@DefaultMessage( "Confirmez-vous la destruction de l''item ?" )
	String confirmItemDelete();

	@DefaultMessage( "Destruction impossible" )
	String editionCatalogueDeleteImpossible();

	@DefaultMessage( "Impossible !" )
	String editionCatalogueImpossibleDeleteRootChapter();

	@DefaultMessage( "Confirmez" )
	String confirmChapterDelete();

	@DefaultMessage( "Renommer le chapitre" )
	String editionCatalogueRenameChapter();

	@DefaultMessage( "Entrez un nouveau nom" )
	String editionCatalogueEnterANewName();

	@DefaultMessage( "Copier" )
	String editionCatalogueCopy();

	@DefaultMessage( "Sélectionnez quelque chose svp" )
	String editionCataloguePleaseSelectSomething();

	@DefaultMessage( "Coller" )
	String editionCataloguePaste();

	@DefaultMessage( "Rien dans le presse papier" )
	String editionCatalogueNothingInPressPaper();

	@DefaultMessage( "Libellé SAP" )
	String articleLibelleSAP();

	@DefaultMessage( "Code SAP" )
	String articleCodeSAP();

	@DefaultMessage( "Tarif SAP" )
	String articleTarifSAP();

	@DefaultMessage( "Code EAN" )
	String articleCodeEAN();

	@DefaultMessage( "Axe" )
	String articleAxe();

	@DefaultMessage( "Statut XIJ" )
	String articleStatutADV();

	@DefaultMessage( "Contenance" )
	String articleContenanceCommerciale();

	@DefaultMessage( "Unité de livraison" )
	String articleUniteLivraison();

	@DefaultMessage( "Hierar. Act." )
	String articleHierarchieActivite();

	@DefaultMessage( "Ligne Commerciale" )
	String articleLigneCommerciale();

	@DefaultMessage( "Utilisation commerciale" )
	String articleUtilisationCommerciale();

	@DefaultMessage( "Marque" )
	String articleMarque();

	@DefaultMessage( "Libellé SFA" )
	String catalogItemLibelleSFA();

	@DefaultMessage( "Conditionnement" )
	String articleConditionnement();

	@DefaultMessage( "Empêcher la saisie de quantité" )
	String catalogItemEmpecherSaisieQuantite();

	@DefaultMessage( "Quantité suggérée" )
	String catalogItemQuantiteSuggeree();

	@DefaultMessage( "Créer un item" )
	String catalogEditionCreateItem();

	@DefaultMessage( "Chercher un article" )
	String catalogEditionSearchArticle();

	@DefaultMessage( "Informations" )
	String catalogEditionSearchArticleInfo();

	@DefaultMessage( "Articles trouvés" )
	String catalogEditionArticlesFound();

	@DefaultMessage( "Ajouter les articles sélectionnés" )
	String catalogEditionAddSelectedArticles();

	@DefaultMessage( "Code SAP" )
	String codeSAP();

	@DefaultMessage( "Libellé" )
	String articleLibelle();

	@DefaultMessage( "Catalogues" )
	String tabCatalogueHeader();

	@DefaultMessage( "Catalogue" )
	String tabCatalogue();

	@DefaultMessage( "Entête du catalogue {0}" )
	String catalogueHeaderTabText( String text );

	@DefaultMessage( "Détails du catalogue {0}" )
	String catalogueDetailTabText( String text );

	@DefaultMessage( "Articles" )
	String article_menu_title();

	@DefaultMessage( "Cache d''application indisponible" )
	String appCacheUnavailable();

	@DefaultMessage( "Cache d''application en état inconnu" )
	String appCacheStatusUnknown();

	@DefaultMessage( "Application non mise en cache" )
	String appCacheStatusUncached();

	@DefaultMessage( "Application à jour ({0})" )
	String appCacheStatusUpToDate( String applicationVersion );

	@DefaultMessage( "APPLICATION OBSOLETE !!!" )
	String appCacheStatusObsolete();

	@DefaultMessage( "Vérification en cours..." )
	String appCacheStatusChecking();

	@DefaultMessage( "Téléchargement d''une nouvelle version" )
	String appCacheStatusDownloading();

	@DefaultMessage( "Mise à jour installée, cliquez pour activer" )
	String appCacheStatusUpdateReady();

	@DefaultMessage( "Base de données locale détruite" )
	String localStroage_cleared();
}
