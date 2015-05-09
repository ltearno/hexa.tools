package fr.lteconsulting.hexa.client.css.bindings;

import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

/**
 * An HexaCss interface to wrap Bootstrap css classes.
 * 
 * Most of the Bootstrap are wrapped here. If some are missing,
 * feel free to submit a pull request !
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public interface BootstrapHexaCss extends HexaCss
{
	static final BootstrapHexaCss CSS = GWT.create( BootstrapHexaCss.class );

	@HexaCssExtra( name = "active" )
	String active();

	@HexaCssExtra( name = "alert" )
	String alert();

	@HexaCssExtra( name = "alert-danger" )
	String alertDanger();

	@HexaCssExtra( name = "alert-info" )
	String alertInfo();

	@HexaCssExtra( name = "alert-success" )
	String alertSuccess();

	@HexaCssExtra( name = "alert-warning" )
	String alertWarning();

	@HexaCssExtra( name = "badge" )
	String badge();

	@HexaCssExtra( name = "btn" )
	String btn();

	@HexaCssExtra( name = "btn-danger" )
	String btnDanger();

	@HexaCssExtra( name = "btn-default" )
	String btnDefault();

	@HexaCssExtra( name = "btn-group" )
	String btnGroup();

	@HexaCssExtra( name = "btn-info" )
	String btnInfo();

	@HexaCssExtra( name = "btn-link" )
	String btnLink();

	@HexaCssExtra( name = "btn-primary" )
	String btnPrimary();

	@HexaCssExtra( name = "btn-success" )
	String btnSuccess();

	@HexaCssExtra( name = "btn-toolbar" )
	String btnToolbar();

	@HexaCssExtra( name = "btn-warning" )
	String btnWarning();

	@HexaCssExtra( name = "callout" )
	String callout();

	@HexaCssExtra( name = "callout-danger" )
	String calloutDanger();

	@HexaCssExtra( name = "callout-warning" )
	String calloutWarning();
	
	@HexaCssExtra( name = "checkbox" )
	String checkbox();

	@HexaCssExtra( name = "col-md-3" )
	String colMd3();

	@HexaCssExtra( name = "col-md-4" )
	String colMd4();

	@HexaCssExtra( name = "col-md-6" )
	String colMd6();

	@HexaCssExtra( name = "col-md-8" )
	String colMd8();
	
	@HexaCssExtra( name = "container-fluid" )
	String containerFluid();

	@HexaCssExtra( name = "disabled" )
	String disabled();

	@HexaCssExtra( name = "form" )
	String form();

	@HexaCssExtra( name = "form-control" )
	String formControl();

	@HexaCssExtra( name = "form-group" )
	String formGroup();

	@HexaCssExtra( name = "img-thumbnail" )
	String imgThumbnail();

	@HexaCssExtra( name = "input-group" )
	String inputGroup();

	@HexaCssExtra( name = "input-group-addon" )
	String inputGroupAddon();

	@HexaCssExtra( name = "label" )
	String label();

	@HexaCssExtra( name = "label-danger" )
	String labelDanger();

	@HexaCssExtra( name = "label-default" )
	String labelDefault();

	@HexaCssExtra( name = "label-info" )
	String labelInfo();

	@HexaCssExtra( name = "label-primary" )
	String labelPrimary();

	@HexaCssExtra( name = "label-success" )
	String labelSuccess();

	@HexaCssExtra( name = "label-warning" )
	String labelWarning();

	@HexaCssExtra( name = "list-group" )
	String listGroup();

	@HexaCssExtra( name = "list-group-item" )
	String listGroupItem();

	@HexaCssExtra( name = "list-group-item-danger" )
	String listGroupItemDanger();

	@HexaCssExtra( name = "list-group-item-heading" )
	String listGroupItemHeading();

	@HexaCssExtra( name = "list-group-item-info" )
	String listGroupItemInfo();

	@HexaCssExtra( name = "list-group-item-success" )
	String listGroupItemSuccess();

	@HexaCssExtra( name = "list-group-item-text" )
	String listGroupItemText();

	@HexaCssExtra( name = "list-group-item-warning" )
	String listGroupItemWarning();

	@HexaCssExtra( name = "page-header" )
	String pageHeader();

	@HexaCssExtra( name = "panel" )
	String panel();

	@HexaCssExtra( name = "panel-body" )
	String panelBody();

	@HexaCssExtra( name = "panel-danger" )
	String panelDanger();

	@HexaCssExtra( name = "panel-default" )
	String panelDefault();

	@HexaCssExtra( name = "panel-footer" )
	String panelFooter();

	@HexaCssExtra( name = "panel-heading" )
	String panelHeading();

	@HexaCssExtra( name = "panel-info" )
	String panelInfo();

	@HexaCssExtra( name = "panel-primary" )
	String panelPrimary();

	@HexaCssExtra( name = "panel-success" )
	String panelSuccess();

	@HexaCssExtra( name = "panel-title" )
	String panelTitle();

	@HexaCssExtra( name = "panel-warning" )
	String panelWarning();

	@HexaCssExtra( name = "progress" )
	String progress();

	@HexaCssExtra( name = "progress-bar" )
	String progressBar();

	@HexaCssExtra( name = "row" )
	String row();

	@HexaCssExtra( name = "table" )
	String table();
	
	@HexaCssExtra( name = "table-striped" )
	String tableStriped();
	
	@HexaCssExtra( name = "table-bordered" )
	String tableBordered();

	@HexaCssExtra( name = "well" )
	String well();
	
	@HexaCssExtra( name = "success" )
	String success();
	
	@HexaCssExtra( name = "info" )
	String info();
	
	@HexaCssExtra( name = "warning" )
	String warning();
	
	@HexaCssExtra( name = "danger" )
	String danger();
}
