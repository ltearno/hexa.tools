package fr.lteconsulting.hexa.client.css.bindings;

import com.google.gwt.core.client.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

/**
 * HexaCss wrapper for the CSS classes defined in the 'pure.css'
 * file from the PureCSS project (http://purecss.io/)
 * 
 * @author Arnaud Tournier
 * www.lteconsulting.fr
 */
public interface PureHexaCss extends HexaCss
{
	public static final PureHexaCss PURE = GWT.create( PureHexaCss.class );

	@HexaCssExtra( name = "hidden" )
	String hidden();

	@HexaCssExtra( name = "opera-only" )
	String operaOnly();

	@HexaCssExtra( name = "pure-button" )
	String button();

	@HexaCssExtra( name = "pure-button-active" )
	String buttonActive();

	@HexaCssExtra( name = "pure-button-disabled" )
	String buttonDisabled();

	@HexaCssExtra( name = "pure-button-hidden" )
	String buttonHidden();

	@HexaCssExtra( name = "pure-button-hover" )
	String buttonHover();

	@HexaCssExtra( name = "pure-button-primary" )
	String buttonPrimary();

	@HexaCssExtra( name = "pure-button-selected" )
	String buttonSelected();

	@HexaCssExtra( name = "pure-checkbox" )
	String checkbox();

	@HexaCssExtra( name = "pure-control-group" )
	String controlGroup();

	@HexaCssExtra( name = "pure-controls" )
	String controls();

	@HexaCssExtra( name = "pure-form" )
	String form();

	@HexaCssExtra( name = "pure-form-aligned" )
	String formAligned();

	@HexaCssExtra( name = "pure-form-message" )
	String formMessage();

	@HexaCssExtra( name = "pure-form-message-inline" )
	String formMessageInline();

	@HexaCssExtra( name = "pure-form-stacked" )
	String formStacked();

	@HexaCssExtra( name = "pure-g" )
	String g();

	@HexaCssExtra( name = "pure-group" )
	String group();

	@HexaCssExtra( name = "pure-help-inline" )
	String helpInline();

	@HexaCssExtra( name = "pure-img" )
	String img();

	@HexaCssExtra( name = "pure-input-1" )
	String input1();

	@HexaCssExtra( name = "pure-input-1-2" )
	String input1_2();

	@HexaCssExtra( name = "pure-input-1-3" )
	String input1_3();

	@HexaCssExtra( name = "pure-input-1-4" )
	String input1_4();

	@HexaCssExtra( name = "pure-input-2-3" )
	String input2_3();

	@HexaCssExtra( name = "pure-input-rounded" )
	String inputRounded();

	@HexaCssExtra( name = "pure-menu" )
	String menu();

	@HexaCssExtra( name = "pure-menu-active" )
	String menuActive();

	@HexaCssExtra( name = "pure-menu-allow-hover" )
	String menuAllowHover();

	@HexaCssExtra( name = "pure-menu-children" )
	String menuChildren();

	@HexaCssExtra( name = "pure-menu-disabled" )
	String menuDisabled();

	@HexaCssExtra( name = "pure-menu-fixed" )
	String menuFixed();

	@HexaCssExtra( name = "pure-menu-has-children" )
	String menuHasChildren();

	@HexaCssExtra( name = "pure-menu-heading" )
	String menuHeading();

	@HexaCssExtra( name = "pure-menu-horizontal" )
	String menuHorizontal();

	@HexaCssExtra( name = "pure-menu-item" )
	String menuItem();

	@HexaCssExtra( name = "pure-menu-link" )
	String menuLink();

	@HexaCssExtra( name = "pure-menu-list" )
	String menuList();

	@HexaCssExtra( name = "pure-menu-scrollable" )
	String menuScrollable();

	@HexaCssExtra( name = "pure-menu-selected" )
	String menuSelected();

	@HexaCssExtra( name = "pure-menu-separator" )
	String menuSeparator();

	@HexaCssExtra( name = "pure-radio" )
	String radio();

	@HexaCssExtra( name = "pure-table" )
	String table();

	@HexaCssExtra( name = "pure-table-bordered" )
	String tableBordered();

	@HexaCssExtra( name = "pure-table-horizontal" )
	String tableHorizontal();

	@HexaCssExtra( name = "pure-table-odd" )
	String tableOdd();

	@HexaCssExtra( name = "pure-table-striped" )
	String tableStriped();

	@HexaCssExtra( name = "pure-u" )
	String u();

	@HexaCssExtra( name = "pure-u-1" )
	String u1();

	@HexaCssExtra( name = "pure-u-1-1" )
	String u1_1();

	@HexaCssExtra( name = "pure-u-1-12" )
	String u1_12();

	@HexaCssExtra( name = "pure-u-1-2" )
	String u1_2();

	@HexaCssExtra( name = "pure-u-1-24" )
	String u1_24();

	@HexaCssExtra( name = "pure-u-1-3" )
	String u1_3();

	@HexaCssExtra( name = "pure-u-1-4" )
	String u1_4();

	@HexaCssExtra( name = "pure-u-1-5" )
	String u1_5();

	@HexaCssExtra( name = "pure-u-1-6" )
	String u1_6();

	@HexaCssExtra( name = "pure-u-1-8" )
	String u1_8();

	@HexaCssExtra( name = "pure-u-10-24" )
	String u10_24();

	@HexaCssExtra( name = "pure-u-11-12" )
	String u11_12();

	@HexaCssExtra( name = "pure-u-11-24" )
	String u11_24();

	@HexaCssExtra( name = "pure-u-12-24" )
	String u12_24();

	@HexaCssExtra( name = "pure-u-13-24" )
	String u13_24();

	@HexaCssExtra( name = "pure-u-14-24" )
	String u14_24();

	@HexaCssExtra( name = "pure-u-15-24" )
	String u15_24();

	@HexaCssExtra( name = "pure-u-16-24" )
	String u16_24();

	@HexaCssExtra( name = "pure-u-17-24" )
	String u17_24();

	@HexaCssExtra( name = "pure-u-18-24" )
	String u18_24();

	@HexaCssExtra( name = "pure-u-19-24" )
	String u19_24();

	@HexaCssExtra( name = "pure-u-2-24" )
	String u2_24();

	@HexaCssExtra( name = "pure-u-2-3" )
	String u2_3();

	@HexaCssExtra( name = "pure-u-2-5" )
	String u2_5();

	@HexaCssExtra( name = "pure-u-20-24" )
	String u20_24();

	@HexaCssExtra( name = "pure-u-21-24" )
	String u21_24();

	@HexaCssExtra( name = "pure-u-22-24" )
	String u22_24();

	@HexaCssExtra( name = "pure-u-23-24" )
	String u23_24();

	@HexaCssExtra( name = "pure-u-24-24" )
	String u24_24();

	@HexaCssExtra( name = "pure-u-3-24" )
	String u3_24();

	@HexaCssExtra( name = "pure-u-3-4" )
	String u3_4();

	@HexaCssExtra( name = "pure-u-3-5" )
	String u3_5();

	@HexaCssExtra( name = "pure-u-3-8" )
	String u3_8();

	@HexaCssExtra( name = "pure-u-4-24" )
	String u4_24();

	@HexaCssExtra( name = "pure-u-4-5" )
	String u4_5();

	@HexaCssExtra( name = "pure-u-5-12" )
	String u5_12();

	@HexaCssExtra( name = "pure-u-5-24" )
	String u5_24();

	@HexaCssExtra( name = "pure-u-5-5" )
	String u5_5();

	@HexaCssExtra( name = "pure-u-5-6" )
	String u5_6();

	@HexaCssExtra( name = "pure-u-5-8" )
	String u5_8();

	@HexaCssExtra( name = "pure-u-6-24" )
	String u6_24();

	@HexaCssExtra( name = "pure-u-7-12" )
	String u7_12();

	@HexaCssExtra( name = "pure-u-7-24" )
	String u7_24();

	@HexaCssExtra( name = "pure-u-7-8" )
	String u7_8();

	@HexaCssExtra( name = "pure-u-8-24" )
	String u8_24();

	@HexaCssExtra( name = "pure-u-9-24" )
	String u9_24();
}