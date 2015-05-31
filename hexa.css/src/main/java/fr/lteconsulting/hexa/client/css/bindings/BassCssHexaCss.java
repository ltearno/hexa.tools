package fr.lteconsulting.hexa.client.css.bindings;

import com.google.gwt.core.client.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

public interface BassCssHexaCss extends HexaCss
{
	public static final BassCssHexaCss CSS = GWT.create( BassCssHexaCss.class );

	@HexaCssExtra( name = "absolute" )
	String absolute();

	@HexaCssExtra( name = "aqua" )
	String aqua();

	@HexaCssExtra( name = "bg-aqua" )
	String bgAqua();

	@HexaCssExtra( name = "bg-black" )
	String bgBlack();

	@HexaCssExtra( name = "bg-blue" )
	String bgBlue();

	@HexaCssExtra( name = "bg-bottom" )
	String bgBottom();

	@HexaCssExtra( name = "bg-center" )
	String bgCenter();

	@HexaCssExtra( name = "bg-contain" )
	String bgContain();

	@HexaCssExtra( name = "bg-cover" )
	String bgCover();

	@HexaCssExtra( name = "bg-dark-gray" )
	String bgDarkGray();

	@HexaCssExtra( name = "bg-darken-1" )
	String bgDarken1();

	@HexaCssExtra( name = "bg-darken-2" )
	String bgDarken2();

	@HexaCssExtra( name = "bg-darken-3" )
	String bgDarken3();

	@HexaCssExtra( name = "bg-darken-4" )
	String bgDarken4();

	@HexaCssExtra( name = "bg-fuchsia" )
	String bgFuchsia();

	@HexaCssExtra( name = "bg-gray" )
	String bgGray();

	@HexaCssExtra( name = "bg-green" )
	String bgGreen();

	@HexaCssExtra( name = "bg-left" )
	String bgLeft();

	@HexaCssExtra( name = "bg-light-gray" )
	String bgLightGray();

	@HexaCssExtra( name = "bg-lime" )
	String bgLime();

	@HexaCssExtra( name = "bg-maroon" )
	String bgMaroon();

	@HexaCssExtra( name = "bg-mid-gray" )
	String bgMidGray();

	@HexaCssExtra( name = "bg-navy" )
	String bgNavy();

	@HexaCssExtra( name = "bg-olive" )
	String bgOlive();

	@HexaCssExtra( name = "bg-orange" )
	String bgOrange();

	@HexaCssExtra( name = "bg-purple" )
	String bgPurple();

	@HexaCssExtra( name = "bg-red" )
	String bgRed();

	@HexaCssExtra( name = "bg-right" )
	String bgRight();

	@HexaCssExtra( name = "bg-silver" )
	String bgSilver();

	@HexaCssExtra( name = "bg-teal" )
	String bgTeal();

	@HexaCssExtra( name = "bg-top" )
	String bgTop();

	@HexaCssExtra( name = "bg-white" )
	String bgWhite();

	@HexaCssExtra( name = "bg-yellow" )
	String bgYellow();

	@HexaCssExtra( name = "black" )
	String black();

	@HexaCssExtra( name = "block" )
	String block();

	@HexaCssExtra( name = "blue" )
	String blue();

	@HexaCssExtra( name = "bold" )
	String bold();

	@HexaCssExtra( name = "border" )
	String border();

	@HexaCssExtra( name = "border-aqua" )
	String borderAqua();

	@HexaCssExtra( name = "border-black" )
	String borderBlack();

	@HexaCssExtra( name = "border-blue" )
	String borderBlue();

	@HexaCssExtra( name = "border-bottom" )
	String borderBottom();

	@HexaCssExtra( name = "border-darken-1" )
	String borderDarken1();

	@HexaCssExtra( name = "border-darken-2" )
	String borderDarken2();

	@HexaCssExtra( name = "border-darken-3" )
	String borderDarken3();

	@HexaCssExtra( name = "border-darken-4" )
	String borderDarken4();

	@HexaCssExtra( name = "border-fuchsia" )
	String borderFuchsia();

	@HexaCssExtra( name = "border-gray" )
	String borderGray();

	@HexaCssExtra( name = "border-green" )
	String borderGreen();

	@HexaCssExtra( name = "border-left" )
	String borderLeft();

	@HexaCssExtra( name = "border-lime" )
	String borderLime();

	@HexaCssExtra( name = "border-maroon" )
	String borderMaroon();

	@HexaCssExtra( name = "border-navy" )
	String borderNavy();

	@HexaCssExtra( name = "border-olive" )
	String borderOlive();

	@HexaCssExtra( name = "border-orange" )
	String borderOrange();

	@HexaCssExtra( name = "border-purple" )
	String borderPurple();

	@HexaCssExtra( name = "border-red" )
	String borderRed();

	@HexaCssExtra( name = "border-right" )
	String borderRight();

	@HexaCssExtra( name = "border-silver" )
	String borderSilver();

	@HexaCssExtra( name = "border-teal" )
	String borderTeal();

	@HexaCssExtra( name = "border-top" )
	String borderTop();

	@HexaCssExtra( name = "border-white" )
	String borderWhite();

	@HexaCssExtra( name = "border-yellow" )
	String borderYellow();

	@HexaCssExtra( name = "bottom-0" )
	String bottom0();

	@HexaCssExtra( name = "break-word" )
	String breakWord();

	@HexaCssExtra( name = "button" )
	String button();

	@HexaCssExtra( name = "button-big" )
	String buttonBig();

	@HexaCssExtra( name = "button-narrow" )
	String buttonNarrow();

	@HexaCssExtra( name = "button-outline" )
	String buttonOutline();

	@HexaCssExtra( name = "button-small" )
	String buttonSmall();

	@HexaCssExtra( name = "button-transparent" )
	String buttonTransparent();

	@HexaCssExtra( name = "caps" )
	String caps();

	@HexaCssExtra( name = "center" )
	String center();

	@HexaCssExtra( name = "circle" )
	String circle();

	@HexaCssExtra( name = "clearfix" )
	String clearfix();

	@HexaCssExtra( name = "col" )
	String col();

	@HexaCssExtra( name = "col-1" )
	String col1();

	@HexaCssExtra( name = "col-10" )
	String col10();

	@HexaCssExtra( name = "col-11" )
	String col11();

	@HexaCssExtra( name = "col-12" )
	String col12();

	@HexaCssExtra( name = "col-2" )
	String col2();

	@HexaCssExtra( name = "col-3" )
	String col3();

	@HexaCssExtra( name = "col-4" )
	String col4();

	@HexaCssExtra( name = "col-5" )
	String col5();

	@HexaCssExtra( name = "col-6" )
	String col6();

	@HexaCssExtra( name = "col-7" )
	String col7();

	@HexaCssExtra( name = "col-8" )
	String col8();

	@HexaCssExtra( name = "col-9" )
	String col9();

	@HexaCssExtra( name = "col-right" )
	String colRight();

	@HexaCssExtra( name = "color-inherit" )
	String colorInherit();

	@HexaCssExtra( name = "container" )
	String container();

	@HexaCssExtra( name = "dark-gray" )
	String darkGray();

	@HexaCssExtra( name = "display-none" )
	String displayNone();

	@HexaCssExtra( name = "field-dark" )
	String fieldDark();

	@HexaCssExtra( name = "field-light" )
	String fieldLight();

	@HexaCssExtra( name = "fieldset-reset" )
	String fieldsetReset();

	@HexaCssExtra( name = "fit" )
	String fit();

	@HexaCssExtra( name = "fixed" )
	String fixed();

	@HexaCssExtra( name = "flex" )
	String flex();

	@HexaCssExtra( name = "flex-auto" )
	String flexAuto();

	@HexaCssExtra( name = "flex-baseline" )
	String flexBaseline();

	@HexaCssExtra( name = "flex-center" )
	String flexCenter();

	@HexaCssExtra( name = "flex-column" )
	String flexColumn();

	@HexaCssExtra( name = "flex-end" )
	String flexEnd();

	@HexaCssExtra( name = "flex-first" )
	String flexFirst();

	@HexaCssExtra( name = "flex-grow" )
	String flexGrow();

	@HexaCssExtra( name = "flex-justify" )
	String flexJustify();

	@HexaCssExtra( name = "flex-last" )
	String flexLast();

	@HexaCssExtra( name = "flex-none" )
	String flexNone();

	@HexaCssExtra( name = "flex-start" )
	String flexStart();

	@HexaCssExtra( name = "flex-stretch" )
	String flexStretch();

	@HexaCssExtra( name = "flex-wrap" )
	String flexWrap();

	@HexaCssExtra( name = "fuchsia" )
	String fuchsia();

	@HexaCssExtra( name = "full-width" )
	String fullWidth();

	@HexaCssExtra( name = "gray" )
	String gray();

	@HexaCssExtra( name = "green" )
	String green();

	@HexaCssExtra( name = "h1" )
	String h1();

	@HexaCssExtra( name = "h2" )
	String h2();

	@HexaCssExtra( name = "h3" )
	String h3();

	@HexaCssExtra( name = "h4" )
	String h4();

	@HexaCssExtra( name = "h5" )
	String h5();

	@HexaCssExtra( name = "h6" )
	String h6();

	@HexaCssExtra( name = "half-width" )
	String halfWidth();

	@HexaCssExtra( name = "hide" )
	String hide();

	@HexaCssExtra( name = "inline" )
	String inline();

	@HexaCssExtra( name = "inline-block" )
	String inlineBlock();

	@HexaCssExtra( name = "is-active" )
	String isActive();

	@HexaCssExtra( name = "is-disabled" )
	String isDisabled();

	@HexaCssExtra( name = "is-error" )
	String isError();

	@HexaCssExtra( name = "is-focused" )
	String isFocused();

	@HexaCssExtra( name = "is-read-only" )
	String isReadOnly();

	@HexaCssExtra( name = "is-success" )
	String isSuccess();

	@HexaCssExtra( name = "is-warning" )
	String isWarning();

	@HexaCssExtra( name = "italic" )
	String italic();

	@HexaCssExtra( name = "justify" )
	String justify();

	@HexaCssExtra( name = "left" )
	String left();

	@HexaCssExtra( name = "left-0" )
	String left0();

	@HexaCssExtra( name = "left-align" )
	String leftAlign();

	@HexaCssExtra( name = "lg-col" )
	String lgCol();

	@HexaCssExtra( name = "lg-col-1" )
	String lgCol1();

	@HexaCssExtra( name = "lg-col-10" )
	String lgCol10();

	@HexaCssExtra( name = "lg-col-11" )
	String lgCol11();

	@HexaCssExtra( name = "lg-col-12" )
	String lgCol12();

	@HexaCssExtra( name = "lg-col-2" )
	String lgCol2();

	@HexaCssExtra( name = "lg-col-3" )
	String lgCol3();

	@HexaCssExtra( name = "lg-col-4" )
	String lgCol4();

	@HexaCssExtra( name = "lg-col-5" )
	String lgCol5();

	@HexaCssExtra( name = "lg-col-6" )
	String lgCol6();

	@HexaCssExtra( name = "lg-col-7" )
	String lgCol7();

	@HexaCssExtra( name = "lg-col-8" )
	String lgCol8();

	@HexaCssExtra( name = "lg-col-9" )
	String lgCol9();

	@HexaCssExtra( name = "lg-col-right" )
	String lgColRight();

	@HexaCssExtra( name = "lg-flex" )
	String lgFlex();

	@HexaCssExtra( name = "lg-hide" )
	String lgHide();

	@HexaCssExtra( name = "lg-show" )
	String lgShow();

	@HexaCssExtra( name = "light-gray" )
	String lightGray();

	@HexaCssExtra( name = "lime" )
	String lime();

	@HexaCssExtra( name = "list-reset" )
	String listReset();

	@HexaCssExtra( name = "m0" )
	String m0();

	@HexaCssExtra( name = "m1" )
	String m1();

	@HexaCssExtra( name = "m2" )
	String m2();

	@HexaCssExtra( name = "m3" )
	String m3();

	@HexaCssExtra( name = "m4" )
	String m4();

	@HexaCssExtra( name = "maroon" )
	String maroon();

	@HexaCssExtra( name = "mb0" )
	String mb0();

	@HexaCssExtra( name = "mb1" )
	String mb1();

	@HexaCssExtra( name = "mb2" )
	String mb2();

	@HexaCssExtra( name = "mb3" )
	String mb3();

	@HexaCssExtra( name = "mb4" )
	String mb4();

	@HexaCssExtra( name = "md-col" )
	String mdCol();

	@HexaCssExtra( name = "md-col-1" )
	String mdCol1();

	@HexaCssExtra( name = "md-col-10" )
	String mdCol10();

	@HexaCssExtra( name = "md-col-11" )
	String mdCol11();

	@HexaCssExtra( name = "md-col-12" )
	String mdCol12();

	@HexaCssExtra( name = "md-col-2" )
	String mdCol2();

	@HexaCssExtra( name = "md-col-3" )
	String mdCol3();

	@HexaCssExtra( name = "md-col-4" )
	String mdCol4();

	@HexaCssExtra( name = "md-col-5" )
	String mdCol5();

	@HexaCssExtra( name = "md-col-6" )
	String mdCol6();

	@HexaCssExtra( name = "md-col-7" )
	String mdCol7();

	@HexaCssExtra( name = "md-col-8" )
	String mdCol8();

	@HexaCssExtra( name = "md-col-9" )
	String mdCol9();

	@HexaCssExtra( name = "md-col-right" )
	String mdColRight();

	@HexaCssExtra( name = "md-flex" )
	String mdFlex();

	@HexaCssExtra( name = "md-hide" )
	String mdHide();

	@HexaCssExtra( name = "md-show" )
	String mdShow();

	@HexaCssExtra( name = "mid-gray" )
	String midGray();

	@HexaCssExtra( name = "ml0" )
	String ml0();

	@HexaCssExtra( name = "ml1" )
	String ml1();

	@HexaCssExtra( name = "ml2" )
	String ml2();

	@HexaCssExtra( name = "ml3" )
	String ml3();

	@HexaCssExtra( name = "ml4" )
	String ml4();

	@HexaCssExtra( name = "mr0" )
	String mr0();

	@HexaCssExtra( name = "mr1" )
	String mr1();

	@HexaCssExtra( name = "mr2" )
	String mr2();

	@HexaCssExtra( name = "mr3" )
	String mr3();

	@HexaCssExtra( name = "mr4" )
	String mr4();

	@HexaCssExtra( name = "mt0" )
	String mt0();

	@HexaCssExtra( name = "mt1" )
	String mt1();

	@HexaCssExtra( name = "mt2" )
	String mt2();

	@HexaCssExtra( name = "mt3" )
	String mt3();

	@HexaCssExtra( name = "mt4" )
	String mt4();

	@HexaCssExtra( name = "muted" )
	String muted();

	@HexaCssExtra( name = "mx-auto" )
	String mxAuto();

	@HexaCssExtra( name = "mxn1" )
	String mxn1();

	@HexaCssExtra( name = "mxn2" )
	String mxn2();

	@HexaCssExtra( name = "mxn3" )
	String mxn3();

	@HexaCssExtra( name = "mxn4" )
	String mxn4();

	@HexaCssExtra( name = "navy" )
	String navy();

	@HexaCssExtra( name = "not-rounded" )
	String notRounded();

	@HexaCssExtra( name = "nowrap" )
	String nowrap();

	@HexaCssExtra( name = "olive" )
	String olive();

	@HexaCssExtra( name = "orange" )
	String orange();

	@HexaCssExtra( name = "overflow-auto" )
	String overflowAuto();

	@HexaCssExtra( name = "overflow-hidden" )
	String overflowHidden();

	@HexaCssExtra( name = "overflow-scroll" )
	String overflowScroll();

	@HexaCssExtra( name = "p1" )
	String p1();

	@HexaCssExtra( name = "p2" )
	String p2();

	@HexaCssExtra( name = "p3" )
	String p3();

	@HexaCssExtra( name = "p4" )
	String p4();

	@HexaCssExtra( name = "progress" )
	String progress();

	@HexaCssExtra( name = "purple" )
	String purple();

	@HexaCssExtra( name = "px1" )
	String px1();

	@HexaCssExtra( name = "px2" )
	String px2();

	@HexaCssExtra( name = "px3" )
	String px3();

	@HexaCssExtra( name = "px4" )
	String px4();

	@HexaCssExtra( name = "py1" )
	String py1();

	@HexaCssExtra( name = "py2" )
	String py2();

	@HexaCssExtra( name = "py3" )
	String py3();

	@HexaCssExtra( name = "py4" )
	String py4();

	@HexaCssExtra( name = "range-light" )
	String rangeLight();

	@HexaCssExtra( name = "red" )
	String red();

	@HexaCssExtra( name = "regular" )
	String regular();

	@HexaCssExtra( name = "relative" )
	String relative();

	@HexaCssExtra( name = "right" )
	String right();

	@HexaCssExtra( name = "right-0" )
	String right0();

	@HexaCssExtra( name = "right-align" )
	String rightAlign();

	@HexaCssExtra( name = "rounded" )
	String rounded();

	@HexaCssExtra( name = "rounded-bottom" )
	String roundedBottom();

	@HexaCssExtra( name = "rounded-left" )
	String roundedLeft();

	@HexaCssExtra( name = "rounded-right" )
	String roundedRight();

	@HexaCssExtra( name = "rounded-top" )
	String roundedTop();

	@HexaCssExtra( name = "silver" )
	String silver();

	@HexaCssExtra( name = "sm-col" )
	String smCol();

	@HexaCssExtra( name = "sm-col-1" )
	String smCol1();

	@HexaCssExtra( name = "sm-col-10" )
	String smCol10();

	@HexaCssExtra( name = "sm-col-11" )
	String smCol11();

	@HexaCssExtra( name = "sm-col-12" )
	String smCol12();

	@HexaCssExtra( name = "sm-col-2" )
	String smCol2();

	@HexaCssExtra( name = "sm-col-3" )
	String smCol3();

	@HexaCssExtra( name = "sm-col-4" )
	String smCol4();

	@HexaCssExtra( name = "sm-col-5" )
	String smCol5();

	@HexaCssExtra( name = "sm-col-6" )
	String smCol6();

	@HexaCssExtra( name = "sm-col-7" )
	String smCol7();

	@HexaCssExtra( name = "sm-col-8" )
	String smCol8();

	@HexaCssExtra( name = "sm-col-9" )
	String smCol9();

	@HexaCssExtra( name = "sm-col-right" )
	String smColRight();

	@HexaCssExtra( name = "sm-flex" )
	String smFlex();

	@HexaCssExtra( name = "sm-hide" )
	String smHide();

	@HexaCssExtra( name = "sm-show" )
	String smShow();

	@HexaCssExtra( name = "table" )
	String table();

	@HexaCssExtra( name = "table-cell" )
	String tableCell();

	@HexaCssExtra( name = "table-light" )
	String tableLight();

	@HexaCssExtra( name = "teal" )
	String teal();

	@HexaCssExtra( name = "top-0" )
	String top0();

	@HexaCssExtra( name = "truncate" )
	String truncate();

	@HexaCssExtra( name = "white" )
	String white();

	@HexaCssExtra( name = "yellow" )
	String yellow();

	@HexaCssExtra( name = "z1" )
	String z1();

	@HexaCssExtra( name = "z2" )
	String z2();

	@HexaCssExtra( name = "z3" )
	String z3();

	@HexaCssExtra( name = "z4" )
	String z4();
}
