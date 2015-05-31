package fr.lteconsulting.hexa.client.css.bindings;

import com.google.gwt.core.client.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

public interface FoundationHexaCss extends HexaCss
{
	public static final FoundationHexaCss CSS = GWT.create( FoundationHexaCss.class );

	@HexaCssExtra( name = "accordion" )
	String accordion();

	@HexaCssExtra( name = "accordion-navigation" )
	String accordionNavigation();

	@HexaCssExtra( name = "active" )
	String active();

	@HexaCssExtra( name = "alert" )
	String alert();

	@HexaCssExtra( name = "alert-box" )
	String alertBox();

	@HexaCssExtra( name = "alert-close" )
	String alertClose();

	@HexaCssExtra( name = "alt" )
	String alt();

	@HexaCssExtra( name = "antialiased" )
	String antialiased();

	@HexaCssExtra( name = "back" )
	String back();

	@HexaCssExtra( name = "bottom" )
	String bottom();

	@HexaCssExtra( name = "breadcrumbs" )
	String breadcrumbs();

	@HexaCssExtra( name = "bullet-item" )
	String bulletItem();

	@HexaCssExtra( name = "button" )
	String button();

	@HexaCssExtra( name = "button-bar" )
	String buttonBar();

	@HexaCssExtra( name = "button-group" )
	String buttonGroup();

	@HexaCssExtra( name = "callout" )
	String callout();

	@HexaCssExtra( name = "carousel" )
	String carousel();

	@HexaCssExtra( name = "circle" )
	String circle();

	@HexaCssExtra( name = "clearfix" )
	String clearfix();

	@HexaCssExtra( name = "clearing-assembled" )
	String clearingAssembled();

	@HexaCssExtra( name = "clearing-blackout" )
	String clearingBlackout();

	@HexaCssExtra( name = "clearing-caption" )
	String clearingCaption();

	@HexaCssExtra( name = "clearing-close" )
	String clearingClose();

	@HexaCssExtra( name = "clearing-container" )
	String clearingContainer();

	@HexaCssExtra( name = "clearing-feature" )
	String clearingFeature();

	@HexaCssExtra( name = "clearing-featured-img" )
	String clearingFeaturedImg();

	@HexaCssExtra( name = "clearing-main-next" )
	String clearingMainNext();

	@HexaCssExtra( name = "clearing-main-prev" )
	String clearingMainPrev();

	@HexaCssExtra( name = "clearing-thumbs" )
	String clearingThumbs();

	@HexaCssExtra( name = "clearing-touch-label" )
	String clearingTouchLabel();

	@HexaCssExtra( name = "close" )
	String close();

	@HexaCssExtra( name = "close-reveal-modal" )
	String closeRevealModal();

	@HexaCssExtra( name = "collapse" )
	String collapse();

	@HexaCssExtra( name = "column" )
	String column();

	@HexaCssExtra( name = "columns" )
	String columns();

	@HexaCssExtra( name = "contain-to-grid" )
	String containToGrid();

	@HexaCssExtra( name = "contained" )
	String contained();

	@HexaCssExtra( name = "content" )
	String content();

	@HexaCssExtra( name = "cta-button" )
	String ctaButton();

	@HexaCssExtra( name = "current" )
	String current();

	@HexaCssExtra( name = "dark" )
	String dark();

	@HexaCssExtra( name = "description" )
	String description();

	@HexaCssExtra( name = "disabled" )
	String disabled();

	@HexaCssExtra( name = "disc" )
	String disc();

	@HexaCssExtra( name = "divider" )
	String divider();

	@HexaCssExtra( name = "drop-left" )
	String dropLeft();

	@HexaCssExtra( name = "drop-right" )
	String dropRight();

	@HexaCssExtra( name = "drop-top" )
	String dropTop();

	@HexaCssExtra( name = "dropdown" )
	String dropdown();

	@HexaCssExtra( name = "eight-up" )
	String eightUp();

	@HexaCssExtra( name = "end" )
	String end();

	@HexaCssExtra( name = "error" )
	String error();

	@HexaCssExtra( name = "error-message" )
	String errorMessage();

	@HexaCssExtra( name = "even" )
	String even();

	@HexaCssExtra( name = "even-2" )
	String even2();

	@HexaCssExtra( name = "even-3" )
	String even3();

	@HexaCssExtra( name = "even-4" )
	String even4();

	@HexaCssExtra( name = "even-5" )
	String even5();

	@HexaCssExtra( name = "even-6" )
	String even6();

	@HexaCssExtra( name = "even-7" )
	String even7();

	@HexaCssExtra( name = "even-8" )
	String even8();

	@HexaCssExtra( name = "exit-off-canvas" )
	String exitOffCanvas();

	@HexaCssExtra( name = "expand" )
	String expand();

	@HexaCssExtra( name = "expanded" )
	String expanded();

	@HexaCssExtra( name = "f-dropdown" )
	String fDropdown();

	@HexaCssExtra( name = "five-up" )
	String fiveUp();

	@HexaCssExtra( name = "fix-height" )
	String fixHeight();

	@HexaCssExtra( name = "fixed" )
	String fixed();

	@HexaCssExtra( name = "flex-video" )
	String flexVideo();

	@HexaCssExtra( name = "fn" )
	String fn();

	@HexaCssExtra( name = "foundation-data-attribute-namespace" )
	String foundationDataAttributeNamespace();

	@HexaCssExtra( name = "foundation-mq-large" )
	String foundationMqLarge();

	@HexaCssExtra( name = "foundation-mq-large-only" )
	String foundationMqLargeOnly();

	@HexaCssExtra( name = "foundation-mq-medium" )
	String foundationMqMedium();

	@HexaCssExtra( name = "foundation-mq-medium-only" )
	String foundationMqMediumOnly();

	@HexaCssExtra( name = "foundation-mq-small" )
	String foundationMqSmall();

	@HexaCssExtra( name = "foundation-mq-small-only" )
	String foundationMqSmallOnly();

	@HexaCssExtra( name = "foundation-mq-topbar" )
	String foundationMqTopbar();

	@HexaCssExtra( name = "foundation-mq-xlarge" )
	String foundationMqXlarge();

	@HexaCssExtra( name = "foundation-mq-xlarge-only" )
	String foundationMqXlargeOnly();

	@HexaCssExtra( name = "foundation-mq-xxlarge" )
	String foundationMqXxlarge();

	@HexaCssExtra( name = "foundation-version" )
	String foundationVersion();

	@HexaCssExtra( name = "four-up" )
	String fourUp();

	@HexaCssExtra( name = "full" )
	String full();

	@HexaCssExtra( name = "has-dropdown" )
	String hasDropdown();

	@HexaCssExtra( name = "has-form" )
	String hasForm();

	@HexaCssExtra( name = "has-submenu" )
	String hasSubmenu();

	@HexaCssExtra( name = "has-tip" )
	String hasTip();

	@HexaCssExtra( name = "heading" )
	String heading();

	@HexaCssExtra( name = "hidden-for-large" )
	String hiddenForLarge();

	@HexaCssExtra( name = "hidden-for-large-down" )
	String hiddenForLargeDown();

	@HexaCssExtra( name = "hidden-for-large-only" )
	String hiddenForLargeOnly();

	@HexaCssExtra( name = "hidden-for-large-up" )
	String hiddenForLargeUp();

	@HexaCssExtra( name = "hidden-for-medium" )
	String hiddenForMedium();

	@HexaCssExtra( name = "hidden-for-medium-down" )
	String hiddenForMediumDown();

	@HexaCssExtra( name = "hidden-for-medium-only" )
	String hiddenForMediumOnly();

	@HexaCssExtra( name = "hidden-for-medium-up" )
	String hiddenForMediumUp();

	@HexaCssExtra( name = "hidden-for-small" )
	String hiddenForSmall();

	@HexaCssExtra( name = "hidden-for-small-down" )
	String hiddenForSmallDown();

	@HexaCssExtra( name = "hidden-for-small-only" )
	String hiddenForSmallOnly();

	@HexaCssExtra( name = "hidden-for-small-up" )
	String hiddenForSmallUp();

	@HexaCssExtra( name = "hidden-for-xlarge" )
	String hiddenForXlarge();

	@HexaCssExtra( name = "hidden-for-xlarge-down" )
	String hiddenForXlargeDown();

	@HexaCssExtra( name = "hidden-for-xlarge-only" )
	String hiddenForXlargeOnly();

	@HexaCssExtra( name = "hidden-for-xlarge-up" )
	String hiddenForXlargeUp();

	@HexaCssExtra( name = "hidden-for-xxlarge" )
	String hiddenForXxlarge();

	@HexaCssExtra( name = "hidden-for-xxlarge-down" )
	String hiddenForXxlargeDown();

	@HexaCssExtra( name = "hidden-for-xxlarge-only" )
	String hiddenForXxlargeOnly();

	@HexaCssExtra( name = "hidden-for-xxlarge-up" )
	String hiddenForXxlargeUp();

	@HexaCssExtra( name = "hide" )
	String hide();

	@HexaCssExtra( name = "hide-for-landscape" )
	String hideForLandscape();

	@HexaCssExtra( name = "hide-for-large" )
	String hideForLarge();

	@HexaCssExtra( name = "hide-for-large-down" )
	String hideForLargeDown();

	@HexaCssExtra( name = "hide-for-large-only" )
	String hideForLargeOnly();

	@HexaCssExtra( name = "hide-for-large-up" )
	String hideForLargeUp();

	@HexaCssExtra( name = "hide-for-medium" )
	String hideForMedium();

	@HexaCssExtra( name = "hide-for-medium-down" )
	String hideForMediumDown();

	@HexaCssExtra( name = "hide-for-medium-only" )
	String hideForMediumOnly();

	@HexaCssExtra( name = "hide-for-medium-up" )
	String hideForMediumUp();

	@HexaCssExtra( name = "hide-for-portrait" )
	String hideForPortrait();

	@HexaCssExtra( name = "hide-for-print" )
	String hideForPrint();

	@HexaCssExtra( name = "hide-for-small" )
	String hideForSmall();

	@HexaCssExtra( name = "hide-for-small-down" )
	String hideForSmallDown();

	@HexaCssExtra( name = "hide-for-small-only" )
	String hideForSmallOnly();

	@HexaCssExtra( name = "hide-for-small-up" )
	String hideForSmallUp();

	@HexaCssExtra( name = "hide-for-touch" )
	String hideForTouch();

	@HexaCssExtra( name = "hide-for-xlarge" )
	String hideForXlarge();

	@HexaCssExtra( name = "hide-for-xlarge-down" )
	String hideForXlargeDown();

	@HexaCssExtra( name = "hide-for-xlarge-only" )
	String hideForXlargeOnly();

	@HexaCssExtra( name = "hide-for-xlarge-up" )
	String hideForXlargeUp();

	@HexaCssExtra( name = "hide-for-xxlarge" )
	String hideForXxlarge();

	@HexaCssExtra( name = "hide-for-xxlarge-down" )
	String hideForXxlargeDown();

	@HexaCssExtra( name = "hide-for-xxlarge-only" )
	String hideForXxlargeOnly();

	@HexaCssExtra( name = "hide-for-xxlarge-up" )
	String hideForXxlargeUp();

	@HexaCssExtra( name = "hide-on-print" )
	String hideOnPrint();

	@HexaCssExtra( name = "hover" )
	String hover();

	@HexaCssExtra( name = "icon-bar" )
	String iconBar();

	@HexaCssExtra( name = "info" )
	String info();

	@HexaCssExtra( name = "inline" )
	String inline();

	@HexaCssExtra( name = "inline-list" )
	String inlineList();

	@HexaCssExtra( name = "inner-wrap" )
	String innerWrap();

	@HexaCssExtra( name = "invisible" )
	String invisible();

	@HexaCssExtra( name = "ir" )
	String ir();

	@HexaCssExtra( name = "item" )
	String item();

	@HexaCssExtra( name = "joyride-close-tip" )
	String joyrideCloseTip();

	@HexaCssExtra( name = "joyride-content-wrapper" )
	String joyrideContentWrapper();

	@HexaCssExtra( name = "joyride-expose-cover" )
	String joyrideExposeCover();

	@HexaCssExtra( name = "joyride-expose-wrapper" )
	String joyrideExposeWrapper();

	@HexaCssExtra( name = "joyride-list" )
	String joyrideList();

	@HexaCssExtra( name = "joyride-modal-bg" )
	String joyrideModalBg();

	@HexaCssExtra( name = "joyride-nub" )
	String joyrideNub();

	@HexaCssExtra( name = "joyride-prev-tip" )
	String joyridePrevTip();

	@HexaCssExtra( name = "joyride-timer-indicator" )
	String joyrideTimerIndicator();

	@HexaCssExtra( name = "joyride-timer-indicator-wrap" )
	String joyrideTimerIndicatorWrap();

	@HexaCssExtra( name = "joyride-tip-guide" )
	String joyrideTipGuide();

	@HexaCssExtra( name = "js-generated" )
	String jsGenerated();

	@HexaCssExtra( name = "keystroke" )
	String keystroke();

	@HexaCssExtra( name = "label" )
	String label();

	@HexaCssExtra( name = "label-right" )
	String labelRight();

	@HexaCssExtra( name = "large" )
	String large();

	@HexaCssExtra( name = "large-1" )
	String large1();

	@HexaCssExtra( name = "large-10" )
	String large10();

	@HexaCssExtra( name = "large-11" )
	String large11();

	@HexaCssExtra( name = "large-12" )
	String large12();

	@HexaCssExtra( name = "large-2" )
	String large2();

	@HexaCssExtra( name = "large-3" )
	String large3();

	@HexaCssExtra( name = "large-4" )
	String large4();

	@HexaCssExtra( name = "large-5" )
	String large5();

	@HexaCssExtra( name = "large-6" )
	String large6();

	@HexaCssExtra( name = "large-7" )
	String large7();

	@HexaCssExtra( name = "large-8" )
	String large8();

	@HexaCssExtra( name = "large-9" )
	String large9();

	@HexaCssExtra( name = "large-block-grid-1" )
	String largeBlockGrid1();

	@HexaCssExtra( name = "large-block-grid-10" )
	String largeBlockGrid10();

	@HexaCssExtra( name = "large-block-grid-11" )
	String largeBlockGrid11();

	@HexaCssExtra( name = "large-block-grid-12" )
	String largeBlockGrid12();

	@HexaCssExtra( name = "large-block-grid-2" )
	String largeBlockGrid2();

	@HexaCssExtra( name = "large-block-grid-3" )
	String largeBlockGrid3();

	@HexaCssExtra( name = "large-block-grid-4" )
	String largeBlockGrid4();

	@HexaCssExtra( name = "large-block-grid-5" )
	String largeBlockGrid5();

	@HexaCssExtra( name = "large-block-grid-6" )
	String largeBlockGrid6();

	@HexaCssExtra( name = "large-block-grid-7" )
	String largeBlockGrid7();

	@HexaCssExtra( name = "large-block-grid-8" )
	String largeBlockGrid8();

	@HexaCssExtra( name = "large-block-grid-9" )
	String largeBlockGrid9();

	@HexaCssExtra( name = "large-centered" )
	String largeCentered();

	@HexaCssExtra( name = "large-collapse" )
	String largeCollapse();

	@HexaCssExtra( name = "large-offset-0" )
	String largeOffset0();

	@HexaCssExtra( name = "large-offset-1" )
	String largeOffset1();

	@HexaCssExtra( name = "large-offset-10" )
	String largeOffset10();

	@HexaCssExtra( name = "large-offset-11" )
	String largeOffset11();

	@HexaCssExtra( name = "large-offset-2" )
	String largeOffset2();

	@HexaCssExtra( name = "large-offset-3" )
	String largeOffset3();

	@HexaCssExtra( name = "large-offset-4" )
	String largeOffset4();

	@HexaCssExtra( name = "large-offset-5" )
	String largeOffset5();

	@HexaCssExtra( name = "large-offset-6" )
	String largeOffset6();

	@HexaCssExtra( name = "large-offset-7" )
	String largeOffset7();

	@HexaCssExtra( name = "large-offset-8" )
	String largeOffset8();

	@HexaCssExtra( name = "large-offset-9" )
	String largeOffset9();

	@HexaCssExtra( name = "large-only-text-center" )
	String largeOnlyTextCenter();

	@HexaCssExtra( name = "large-only-text-justify" )
	String largeOnlyTextJustify();

	@HexaCssExtra( name = "large-only-text-left" )
	String largeOnlyTextLeft();

	@HexaCssExtra( name = "large-only-text-right" )
	String largeOnlyTextRight();

	@HexaCssExtra( name = "large-pull-0" )
	String largePull0();

	@HexaCssExtra( name = "large-pull-1" )
	String largePull1();

	@HexaCssExtra( name = "large-pull-10" )
	String largePull10();

	@HexaCssExtra( name = "large-pull-11" )
	String largePull11();

	@HexaCssExtra( name = "large-pull-2" )
	String largePull2();

	@HexaCssExtra( name = "large-pull-3" )
	String largePull3();

	@HexaCssExtra( name = "large-pull-4" )
	String largePull4();

	@HexaCssExtra( name = "large-pull-5" )
	String largePull5();

	@HexaCssExtra( name = "large-pull-6" )
	String largePull6();

	@HexaCssExtra( name = "large-pull-7" )
	String largePull7();

	@HexaCssExtra( name = "large-pull-8" )
	String largePull8();

	@HexaCssExtra( name = "large-pull-9" )
	String largePull9();

	@HexaCssExtra( name = "large-push-0" )
	String largePush0();

	@HexaCssExtra( name = "large-push-1" )
	String largePush1();

	@HexaCssExtra( name = "large-push-10" )
	String largePush10();

	@HexaCssExtra( name = "large-push-11" )
	String largePush11();

	@HexaCssExtra( name = "large-push-2" )
	String largePush2();

	@HexaCssExtra( name = "large-push-3" )
	String largePush3();

	@HexaCssExtra( name = "large-push-4" )
	String largePush4();

	@HexaCssExtra( name = "large-push-5" )
	String largePush5();

	@HexaCssExtra( name = "large-push-6" )
	String largePush6();

	@HexaCssExtra( name = "large-push-7" )
	String largePush7();

	@HexaCssExtra( name = "large-push-8" )
	String largePush8();

	@HexaCssExtra( name = "large-push-9" )
	String largePush9();

	@HexaCssExtra( name = "large-reset-order" )
	String largeResetOrder();

	@HexaCssExtra( name = "large-text-center" )
	String largeTextCenter();

	@HexaCssExtra( name = "large-text-justify" )
	String largeTextJustify();

	@HexaCssExtra( name = "large-text-left" )
	String largeTextLeft();

	@HexaCssExtra( name = "large-text-right" )
	String largeTextRight();

	@HexaCssExtra( name = "large-uncentered" )
	String largeUncentered();

	@HexaCssExtra( name = "large-uncollapse" )
	String largeUncollapse();

	@HexaCssExtra( name = "large-vertical" )
	String largeVertical();

	@HexaCssExtra( name = "lead" )
	String lead();

	@HexaCssExtra( name = "left" )
	String left();

	@HexaCssExtra( name = "left-align" )
	String leftAlign();

	@HexaCssExtra( name = "left-off-canvas-menu" )
	String leftOffCanvasMenu();

	@HexaCssExtra( name = "left-small" )
	String leftSmall();

	@HexaCssExtra( name = "left-submenu" )
	String leftSubmenu();

	@HexaCssExtra( name = "lt-ie9" )
	String ltIe9();

	@HexaCssExtra( name = "map_canvas" )
	String map_canvas();

	@HexaCssExtra( name = "medium" )
	String medium();

	@HexaCssExtra( name = "medium-1" )
	String medium1();

	@HexaCssExtra( name = "medium-10" )
	String medium10();

	@HexaCssExtra( name = "medium-11" )
	String medium11();

	@HexaCssExtra( name = "medium-12" )
	String medium12();

	@HexaCssExtra( name = "medium-2" )
	String medium2();

	@HexaCssExtra( name = "medium-3" )
	String medium3();

	@HexaCssExtra( name = "medium-4" )
	String medium4();

	@HexaCssExtra( name = "medium-5" )
	String medium5();

	@HexaCssExtra( name = "medium-6" )
	String medium6();

	@HexaCssExtra( name = "medium-7" )
	String medium7();

	@HexaCssExtra( name = "medium-8" )
	String medium8();

	@HexaCssExtra( name = "medium-9" )
	String medium9();

	@HexaCssExtra( name = "medium-block-grid-1" )
	String mediumBlockGrid1();

	@HexaCssExtra( name = "medium-block-grid-10" )
	String mediumBlockGrid10();

	@HexaCssExtra( name = "medium-block-grid-11" )
	String mediumBlockGrid11();

	@HexaCssExtra( name = "medium-block-grid-12" )
	String mediumBlockGrid12();

	@HexaCssExtra( name = "medium-block-grid-2" )
	String mediumBlockGrid2();

	@HexaCssExtra( name = "medium-block-grid-3" )
	String mediumBlockGrid3();

	@HexaCssExtra( name = "medium-block-grid-4" )
	String mediumBlockGrid4();

	@HexaCssExtra( name = "medium-block-grid-5" )
	String mediumBlockGrid5();

	@HexaCssExtra( name = "medium-block-grid-6" )
	String mediumBlockGrid6();

	@HexaCssExtra( name = "medium-block-grid-7" )
	String mediumBlockGrid7();

	@HexaCssExtra( name = "medium-block-grid-8" )
	String mediumBlockGrid8();

	@HexaCssExtra( name = "medium-block-grid-9" )
	String mediumBlockGrid9();

	@HexaCssExtra( name = "medium-centered" )
	String mediumCentered();

	@HexaCssExtra( name = "medium-collapse" )
	String mediumCollapse();

	@HexaCssExtra( name = "medium-offset-0" )
	String mediumOffset0();

	@HexaCssExtra( name = "medium-offset-1" )
	String mediumOffset1();

	@HexaCssExtra( name = "medium-offset-10" )
	String mediumOffset10();

	@HexaCssExtra( name = "medium-offset-11" )
	String mediumOffset11();

	@HexaCssExtra( name = "medium-offset-2" )
	String mediumOffset2();

	@HexaCssExtra( name = "medium-offset-3" )
	String mediumOffset3();

	@HexaCssExtra( name = "medium-offset-4" )
	String mediumOffset4();

	@HexaCssExtra( name = "medium-offset-5" )
	String mediumOffset5();

	@HexaCssExtra( name = "medium-offset-6" )
	String mediumOffset6();

	@HexaCssExtra( name = "medium-offset-7" )
	String mediumOffset7();

	@HexaCssExtra( name = "medium-offset-8" )
	String mediumOffset8();

	@HexaCssExtra( name = "medium-offset-9" )
	String mediumOffset9();

	@HexaCssExtra( name = "medium-only-text-center" )
	String mediumOnlyTextCenter();

	@HexaCssExtra( name = "medium-only-text-justify" )
	String mediumOnlyTextJustify();

	@HexaCssExtra( name = "medium-only-text-left" )
	String mediumOnlyTextLeft();

	@HexaCssExtra( name = "medium-only-text-right" )
	String mediumOnlyTextRight();

	@HexaCssExtra( name = "medium-pull-0" )
	String mediumPull0();

	@HexaCssExtra( name = "medium-pull-1" )
	String mediumPull1();

	@HexaCssExtra( name = "medium-pull-10" )
	String mediumPull10();

	@HexaCssExtra( name = "medium-pull-11" )
	String mediumPull11();

	@HexaCssExtra( name = "medium-pull-2" )
	String mediumPull2();

	@HexaCssExtra( name = "medium-pull-3" )
	String mediumPull3();

	@HexaCssExtra( name = "medium-pull-4" )
	String mediumPull4();

	@HexaCssExtra( name = "medium-pull-5" )
	String mediumPull5();

	@HexaCssExtra( name = "medium-pull-6" )
	String mediumPull6();

	@HexaCssExtra( name = "medium-pull-7" )
	String mediumPull7();

	@HexaCssExtra( name = "medium-pull-8" )
	String mediumPull8();

	@HexaCssExtra( name = "medium-pull-9" )
	String mediumPull9();

	@HexaCssExtra( name = "medium-push-0" )
	String mediumPush0();

	@HexaCssExtra( name = "medium-push-1" )
	String mediumPush1();

	@HexaCssExtra( name = "medium-push-10" )
	String mediumPush10();

	@HexaCssExtra( name = "medium-push-11" )
	String mediumPush11();

	@HexaCssExtra( name = "medium-push-2" )
	String mediumPush2();

	@HexaCssExtra( name = "medium-push-3" )
	String mediumPush3();

	@HexaCssExtra( name = "medium-push-4" )
	String mediumPush4();

	@HexaCssExtra( name = "medium-push-5" )
	String mediumPush5();

	@HexaCssExtra( name = "medium-push-6" )
	String mediumPush6();

	@HexaCssExtra( name = "medium-push-7" )
	String mediumPush7();

	@HexaCssExtra( name = "medium-push-8" )
	String mediumPush8();

	@HexaCssExtra( name = "medium-push-9" )
	String mediumPush9();

	@HexaCssExtra( name = "medium-reset-order" )
	String mediumResetOrder();

	@HexaCssExtra( name = "medium-text-center" )
	String mediumTextCenter();

	@HexaCssExtra( name = "medium-text-justify" )
	String mediumTextJustify();

	@HexaCssExtra( name = "medium-text-left" )
	String mediumTextLeft();

	@HexaCssExtra( name = "medium-text-right" )
	String mediumTextRight();

	@HexaCssExtra( name = "medium-uncentered" )
	String mediumUncentered();

	@HexaCssExtra( name = "medium-uncollapse" )
	String mediumUncollapse();

	@HexaCssExtra( name = "medium-vertical" )
	String mediumVertical();

	@HexaCssExtra( name = "mega" )
	String mega();

	@HexaCssExtra( name = "menu-icon" )
	String menuIcon();

	@HexaCssExtra( name = "meter" )
	String meter();

	@HexaCssExtra( name = "middle" )
	String middle();

	@HexaCssExtra( name = "move-left" )
	String moveLeft();

	@HexaCssExtra( name = "move-right" )
	String moveRight();

	@HexaCssExtra( name = "moved" )
	String moved();

	@HexaCssExtra( name = "mqa-display" )
	String mqaDisplay();

	@HexaCssExtra( name = "name" )
	String name();

	@HexaCssExtra( name = "no-bullet" )
	String noBullet();

	@HexaCssExtra( name = "no-csstransforms" )
	String noCsstransforms();

	@HexaCssExtra( name = "no-js" )
	String noJs();

	@HexaCssExtra( name = "no-pip" )
	String noPip();

	@HexaCssExtra( name = "not-click" )
	String notClick();

	@HexaCssExtra( name = "nub" )
	String nub();

	@HexaCssExtra( name = "off-canvas-list" )
	String offCanvasList();

	@HexaCssExtra( name = "off-canvas-wrap" )
	String offCanvasWrap();

	@HexaCssExtra( name = "offcanvas-overlap" )
	String offcanvasOverlap();

	@HexaCssExtra( name = "offcanvas-overlap-left" )
	String offcanvasOverlapLeft();

	@HexaCssExtra( name = "offcanvas-overlap-right" )
	String offcanvasOverlapRight();

	@HexaCssExtra( name = "open" )
	String open();

	@HexaCssExtra( name = "opened" )
	String opened();

	@HexaCssExtra( name = "opposite" )
	String opposite();

	@HexaCssExtra( name = "orbit-bullets" )
	String orbitBullets();

	@HexaCssExtra( name = "orbit-bullets-container" )
	String orbitBulletsContainer();

	@HexaCssExtra( name = "orbit-caption" )
	String orbitCaption();

	@HexaCssExtra( name = "orbit-container" )
	String orbitContainer();

	@HexaCssExtra( name = "orbit-next" )
	String orbitNext();

	@HexaCssExtra( name = "orbit-prev" )
	String orbitPrev();

	@HexaCssExtra( name = "orbit-progress" )
	String orbitProgress();

	@HexaCssExtra( name = "orbit-slide-number" )
	String orbitSlideNumber();

	@HexaCssExtra( name = "orbit-slides-container" )
	String orbitSlidesContainer();

	@HexaCssExtra( name = "orbit-stack-on-small" )
	String orbitStackOnSmall();

	@HexaCssExtra( name = "orbit-timer" )
	String orbitTimer();

	@HexaCssExtra( name = "pagination" )
	String pagination();

	@HexaCssExtra( name = "pagination-centered" )
	String paginationCentered();

	@HexaCssExtra( name = "panel" )
	String panel();

	@HexaCssExtra( name = "parent-link" )
	String parentLink();

	@HexaCssExtra( name = "paused" )
	String paused();

	@HexaCssExtra( name = "postfix" )
	String postfix();

	@HexaCssExtra( name = "postfix-radius" )
	String postfixRadius();

	@HexaCssExtra( name = "postfix-round" )
	String postfixRound();

	@HexaCssExtra( name = "prefix" )
	String prefix();

	@HexaCssExtra( name = "prefix-radius" )
	String prefixRadius();

	@HexaCssExtra( name = "prefix-round" )
	String prefixRound();

	@HexaCssExtra( name = "preloader" )
	String preloader();

	@HexaCssExtra( name = "price" )
	String price();

	@HexaCssExtra( name = "pricing-table" )
	String pricingTable();

	@HexaCssExtra( name = "print-only" )
	String printOnly();

	@HexaCssExtra( name = "progress" )
	String progress();

	@HexaCssExtra( name = "pull-0" )
	String pull0();

	@HexaCssExtra( name = "pull-1" )
	String pull1();

	@HexaCssExtra( name = "pull-10" )
	String pull10();

	@HexaCssExtra( name = "pull-11" )
	String pull11();

	@HexaCssExtra( name = "pull-2" )
	String pull2();

	@HexaCssExtra( name = "pull-3" )
	String pull3();

	@HexaCssExtra( name = "pull-4" )
	String pull4();

	@HexaCssExtra( name = "pull-5" )
	String pull5();

	@HexaCssExtra( name = "pull-6" )
	String pull6();

	@HexaCssExtra( name = "pull-7" )
	String pull7();

	@HexaCssExtra( name = "pull-8" )
	String pull8();

	@HexaCssExtra( name = "pull-9" )
	String pull9();

	@HexaCssExtra( name = "push-0" )
	String push0();

	@HexaCssExtra( name = "push-1" )
	String push1();

	@HexaCssExtra( name = "push-10" )
	String push10();

	@HexaCssExtra( name = "push-11" )
	String push11();

	@HexaCssExtra( name = "push-2" )
	String push2();

	@HexaCssExtra( name = "push-3" )
	String push3();

	@HexaCssExtra( name = "push-4" )
	String push4();

	@HexaCssExtra( name = "push-5" )
	String push5();

	@HexaCssExtra( name = "push-6" )
	String push6();

	@HexaCssExtra( name = "push-7" )
	String push7();

	@HexaCssExtra( name = "push-8" )
	String push8();

	@HexaCssExtra( name = "push-9" )
	String push9();

	@HexaCssExtra( name = "radius" )
	String radius();

	@HexaCssExtra( name = "range-slider" )
	String rangeSlider();

	@HexaCssExtra( name = "range-slider-active-segment" )
	String rangeSliderActiveSegment();

	@HexaCssExtra( name = "range-slider-handle" )
	String rangeSliderHandle();

	@HexaCssExtra( name = "reveal-modal" )
	String revealModal();

	@HexaCssExtra( name = "reveal-modal-bg" )
	String revealModalBg();

	@HexaCssExtra( name = "right" )
	String right();

	@HexaCssExtra( name = "right-align" )
	String rightAlign();

	@HexaCssExtra( name = "right-off-canvas-menu" )
	String rightOffCanvasMenu();

	@HexaCssExtra( name = "right-small" )
	String rightSmall();

	@HexaCssExtra( name = "right-submenu" )
	String rightSubmenu();

	@HexaCssExtra( name = "round" )
	String round();

	@HexaCssExtra( name = "row" )
	String row();

	@HexaCssExtra( name = "rtl" )
	String rtl();

	@HexaCssExtra( name = "secondary" )
	String secondary();

	@HexaCssExtra( name = "seven-up" )
	String sevenUp();

	@HexaCssExtra( name = "show-for-landscape" )
	String showForLandscape();

	@HexaCssExtra( name = "show-for-large" )
	String showForLarge();

	@HexaCssExtra( name = "show-for-large-down" )
	String showForLargeDown();

	@HexaCssExtra( name = "show-for-large-only" )
	String showForLargeOnly();

	@HexaCssExtra( name = "show-for-large-up" )
	String showForLargeUp();

	@HexaCssExtra( name = "show-for-medium" )
	String showForMedium();

	@HexaCssExtra( name = "show-for-medium-down" )
	String showForMediumDown();

	@HexaCssExtra( name = "show-for-medium-only" )
	String showForMediumOnly();

	@HexaCssExtra( name = "show-for-medium-up" )
	String showForMediumUp();

	@HexaCssExtra( name = "show-for-portrait" )
	String showForPortrait();

	@HexaCssExtra( name = "show-for-print" )
	String showForPrint();

	@HexaCssExtra( name = "show-for-small" )
	String showForSmall();

	@HexaCssExtra( name = "show-for-small-down" )
	String showForSmallDown();

	@HexaCssExtra( name = "show-for-small-only" )
	String showForSmallOnly();

	@HexaCssExtra( name = "show-for-small-up" )
	String showForSmallUp();

	@HexaCssExtra( name = "show-for-sr" )
	String showForSr();

	@HexaCssExtra( name = "show-for-touch" )
	String showForTouch();

	@HexaCssExtra( name = "show-for-xlarge" )
	String showForXlarge();

	@HexaCssExtra( name = "show-for-xlarge-down" )
	String showForXlargeDown();

	@HexaCssExtra( name = "show-for-xlarge-only" )
	String showForXlargeOnly();

	@HexaCssExtra( name = "show-for-xlarge-up" )
	String showForXlargeUp();

	@HexaCssExtra( name = "show-for-xxlarge" )
	String showForXxlarge();

	@HexaCssExtra( name = "show-for-xxlarge-down" )
	String showForXxlargeDown();

	@HexaCssExtra( name = "show-for-xxlarge-only" )
	String showForXxlargeOnly();

	@HexaCssExtra( name = "show-for-xxlarge-up" )
	String showForXxlargeUp();

	@HexaCssExtra( name = "show-on-focus" )
	String showOnFocus();

	@HexaCssExtra( name = "side-nav" )
	String sideNav();

	@HexaCssExtra( name = "six-up" )
	String sixUp();

	@HexaCssExtra( name = "slideshow-wrapper" )
	String slideshowWrapper();

	@HexaCssExtra( name = "small" )
	String small();

	@HexaCssExtra( name = "small-1" )
	String small1();

	@HexaCssExtra( name = "small-10" )
	String small10();

	@HexaCssExtra( name = "small-11" )
	String small11();

	@HexaCssExtra( name = "small-12" )
	String small12();

	@HexaCssExtra( name = "small-2" )
	String small2();

	@HexaCssExtra( name = "small-3" )
	String small3();

	@HexaCssExtra( name = "small-4" )
	String small4();

	@HexaCssExtra( name = "small-5" )
	String small5();

	@HexaCssExtra( name = "small-6" )
	String small6();

	@HexaCssExtra( name = "small-7" )
	String small7();

	@HexaCssExtra( name = "small-8" )
	String small8();

	@HexaCssExtra( name = "small-9" )
	String small9();

	@HexaCssExtra( name = "small-block-grid-1" )
	String smallBlockGrid1();

	@HexaCssExtra( name = "small-block-grid-10" )
	String smallBlockGrid10();

	@HexaCssExtra( name = "small-block-grid-11" )
	String smallBlockGrid11();

	@HexaCssExtra( name = "small-block-grid-12" )
	String smallBlockGrid12();

	@HexaCssExtra( name = "small-block-grid-2" )
	String smallBlockGrid2();

	@HexaCssExtra( name = "small-block-grid-3" )
	String smallBlockGrid3();

	@HexaCssExtra( name = "small-block-grid-4" )
	String smallBlockGrid4();

	@HexaCssExtra( name = "small-block-grid-5" )
	String smallBlockGrid5();

	@HexaCssExtra( name = "small-block-grid-6" )
	String smallBlockGrid6();

	@HexaCssExtra( name = "small-block-grid-7" )
	String smallBlockGrid7();

	@HexaCssExtra( name = "small-block-grid-8" )
	String smallBlockGrid8();

	@HexaCssExtra( name = "small-block-grid-9" )
	String smallBlockGrid9();

	@HexaCssExtra( name = "small-centered" )
	String smallCentered();

	@HexaCssExtra( name = "small-collapse" )
	String smallCollapse();

	@HexaCssExtra( name = "small-offset-0" )
	String smallOffset0();

	@HexaCssExtra( name = "small-offset-1" )
	String smallOffset1();

	@HexaCssExtra( name = "small-offset-10" )
	String smallOffset10();

	@HexaCssExtra( name = "small-offset-11" )
	String smallOffset11();

	@HexaCssExtra( name = "small-offset-2" )
	String smallOffset2();

	@HexaCssExtra( name = "small-offset-3" )
	String smallOffset3();

	@HexaCssExtra( name = "small-offset-4" )
	String smallOffset4();

	@HexaCssExtra( name = "small-offset-5" )
	String smallOffset5();

	@HexaCssExtra( name = "small-offset-6" )
	String smallOffset6();

	@HexaCssExtra( name = "small-offset-7" )
	String smallOffset7();

	@HexaCssExtra( name = "small-offset-8" )
	String smallOffset8();

	@HexaCssExtra( name = "small-offset-9" )
	String smallOffset9();

	@HexaCssExtra( name = "small-only-text-center" )
	String smallOnlyTextCenter();

	@HexaCssExtra( name = "small-only-text-justify" )
	String smallOnlyTextJustify();

	@HexaCssExtra( name = "small-only-text-left" )
	String smallOnlyTextLeft();

	@HexaCssExtra( name = "small-only-text-right" )
	String smallOnlyTextRight();

	@HexaCssExtra( name = "small-pull-0" )
	String smallPull0();

	@HexaCssExtra( name = "small-pull-1" )
	String smallPull1();

	@HexaCssExtra( name = "small-pull-10" )
	String smallPull10();

	@HexaCssExtra( name = "small-pull-11" )
	String smallPull11();

	@HexaCssExtra( name = "small-pull-2" )
	String smallPull2();

	@HexaCssExtra( name = "small-pull-3" )
	String smallPull3();

	@HexaCssExtra( name = "small-pull-4" )
	String smallPull4();

	@HexaCssExtra( name = "small-pull-5" )
	String smallPull5();

	@HexaCssExtra( name = "small-pull-6" )
	String smallPull6();

	@HexaCssExtra( name = "small-pull-7" )
	String smallPull7();

	@HexaCssExtra( name = "small-pull-8" )
	String smallPull8();

	@HexaCssExtra( name = "small-pull-9" )
	String smallPull9();

	@HexaCssExtra( name = "small-push-0" )
	String smallPush0();

	@HexaCssExtra( name = "small-push-1" )
	String smallPush1();

	@HexaCssExtra( name = "small-push-10" )
	String smallPush10();

	@HexaCssExtra( name = "small-push-11" )
	String smallPush11();

	@HexaCssExtra( name = "small-push-2" )
	String smallPush2();

	@HexaCssExtra( name = "small-push-3" )
	String smallPush3();

	@HexaCssExtra( name = "small-push-4" )
	String smallPush4();

	@HexaCssExtra( name = "small-push-5" )
	String smallPush5();

	@HexaCssExtra( name = "small-push-6" )
	String smallPush6();

	@HexaCssExtra( name = "small-push-7" )
	String smallPush7();

	@HexaCssExtra( name = "small-push-8" )
	String smallPush8();

	@HexaCssExtra( name = "small-push-9" )
	String smallPush9();

	@HexaCssExtra( name = "small-reset-order" )
	String smallResetOrder();

	@HexaCssExtra( name = "small-text-center" )
	String smallTextCenter();

	@HexaCssExtra( name = "small-text-justify" )
	String smallTextJustify();

	@HexaCssExtra( name = "small-text-left" )
	String smallTextLeft();

	@HexaCssExtra( name = "small-text-right" )
	String smallTextRight();

	@HexaCssExtra( name = "small-uncentered" )
	String smallUncentered();

	@HexaCssExtra( name = "small-uncollapse" )
	String smallUncollapse();

	@HexaCssExtra( name = "small-vertical" )
	String smallVertical();

	@HexaCssExtra( name = "split" )
	String split();

	@HexaCssExtra( name = "square" )
	String square();

	@HexaCssExtra( name = "stack" )
	String stack();

	@HexaCssExtra( name = "stack-for-small" )
	String stackForSmall();

	@HexaCssExtra( name = "sub-nav" )
	String subNav();

	@HexaCssExtra( name = "subheader" )
	String subheader();

	@HexaCssExtra( name = "success" )
	String success();

	@HexaCssExtra( name = "summary" )
	String summary();

	@HexaCssExtra( name = "switch" )
	String switch_();

	@HexaCssExtra( name = "tab" )
	String tab();

	@HexaCssExtra( name = "tab-bar" )
	String tabBar();

	@HexaCssExtra( name = "tab-bar-section" )
	String tabBarSection();

	@HexaCssExtra( name = "tab-title" )
	String tabTitle();

	@HexaCssExtra( name = "tabs" )
	String tabs();

	@HexaCssExtra( name = "tabs-content" )
	String tabsContent();

	@HexaCssExtra( name = "tap-to-close" )
	String tapToClose();

	@HexaCssExtra( name = "text-center" )
	String textCenter();

	@HexaCssExtra( name = "text-justify" )
	String textJustify();

	@HexaCssExtra( name = "text-left" )
	String textLeft();

	@HexaCssExtra( name = "text-right" )
	String textRight();

	@HexaCssExtra( name = "th" )
	String th();

	@HexaCssExtra( name = "three-up" )
	String threeUp();

	@HexaCssExtra( name = "tiny" )
	String tiny();

	@HexaCssExtra( name = "tip-left" )
	String tipLeft();

	@HexaCssExtra( name = "tip-right" )
	String tipRight();

	@HexaCssExtra( name = "tip-top" )
	String tipTop();

	@HexaCssExtra( name = "title" )
	String title();

	@HexaCssExtra( name = "title-area" )
	String titleArea();

	@HexaCssExtra( name = "toback" )
	String toback();

	@HexaCssExtra( name = "toggle-topbar" )
	String toggleTopbar();

	@HexaCssExtra( name = "tooltip" )
	String tooltip();

	@HexaCssExtra( name = "top" )
	String top();

	@HexaCssExtra( name = "top-bar" )
	String topBar();

	@HexaCssExtra( name = "top-bar-section" )
	String topBarSection();

	@HexaCssExtra( name = "touch" )
	String touch();

	@HexaCssExtra( name = "two-up" )
	String twoUp();

	@HexaCssExtra( name = "unavailable" )
	String unavailable();

	@HexaCssExtra( name = "vcard" )
	String vcard();

	@HexaCssExtra( name = "vertical" )
	String vertical();

	@HexaCssExtra( name = "vertical-range" )
	String verticalRange();

	@HexaCssExtra( name = "vevent" )
	String vevent();

	@HexaCssExtra( name = "vimeo" )
	String vimeo();

	@HexaCssExtra( name = "visible" )
	String visible();

	@HexaCssExtra( name = "visible-for-large" )
	String visibleForLarge();

	@HexaCssExtra( name = "visible-for-large-down" )
	String visibleForLargeDown();

	@HexaCssExtra( name = "visible-for-large-only" )
	String visibleForLargeOnly();

	@HexaCssExtra( name = "visible-for-large-up" )
	String visibleForLargeUp();

	@HexaCssExtra( name = "visible-for-medium" )
	String visibleForMedium();

	@HexaCssExtra( name = "visible-for-medium-down" )
	String visibleForMediumDown();

	@HexaCssExtra( name = "visible-for-medium-only" )
	String visibleForMediumOnly();

	@HexaCssExtra( name = "visible-for-medium-up" )
	String visibleForMediumUp();

	@HexaCssExtra( name = "visible-for-small" )
	String visibleForSmall();

	@HexaCssExtra( name = "visible-for-small-down" )
	String visibleForSmallDown();

	@HexaCssExtra( name = "visible-for-small-only" )
	String visibleForSmallOnly();

	@HexaCssExtra( name = "visible-for-small-up" )
	String visibleForSmallUp();

	@HexaCssExtra( name = "visible-for-xlarge" )
	String visibleForXlarge();

	@HexaCssExtra( name = "visible-for-xlarge-down" )
	String visibleForXlargeDown();

	@HexaCssExtra( name = "visible-for-xlarge-only" )
	String visibleForXlargeOnly();

	@HexaCssExtra( name = "visible-for-xlarge-up" )
	String visibleForXlargeUp();

	@HexaCssExtra( name = "visible-for-xxlarge" )
	String visibleForXxlarge();

	@HexaCssExtra( name = "visible-for-xxlarge-down" )
	String visibleForXxlargeDown();

	@HexaCssExtra( name = "visible-for-xxlarge-only" )
	String visibleForXxlargeOnly();

	@HexaCssExtra( name = "visible-for-xxlarge-up" )
	String visibleForXxlargeUp();

	@HexaCssExtra( name = "visible-img" )
	String visibleImg();

	@HexaCssExtra( name = "warning" )
	String warning();

	@HexaCssExtra( name = "widescreen" )
	String widescreen();

	@HexaCssExtra( name = "xlarge" )
	String xlarge();

	@HexaCssExtra( name = "xlarge-only-text-center" )
	String xlargeOnlyTextCenter();

	@HexaCssExtra( name = "xlarge-only-text-justify" )
	String xlargeOnlyTextJustify();

	@HexaCssExtra( name = "xlarge-only-text-left" )
	String xlargeOnlyTextLeft();

	@HexaCssExtra( name = "xlarge-only-text-right" )
	String xlargeOnlyTextRight();

	@HexaCssExtra( name = "xlarge-text-center" )
	String xlargeTextCenter();

	@HexaCssExtra( name = "xlarge-text-justify" )
	String xlargeTextJustify();

	@HexaCssExtra( name = "xlarge-text-left" )
	String xlargeTextLeft();

	@HexaCssExtra( name = "xlarge-text-right" )
	String xlargeTextRight();

	@HexaCssExtra( name = "xxlarge-only-text-center" )
	String xxlargeOnlyTextCenter();

	@HexaCssExtra( name = "xxlarge-only-text-justify" )
	String xxlargeOnlyTextJustify();

	@HexaCssExtra( name = "xxlarge-only-text-left" )
	String xxlargeOnlyTextLeft();

	@HexaCssExtra( name = "xxlarge-only-text-right" )
	String xxlargeOnlyTextRight();

	@HexaCssExtra( name = "xxlarge-text-center" )
	String xxlargeTextCenter();

	@HexaCssExtra( name = "xxlarge-text-justify" )
	String xxlargeTextJustify();

	@HexaCssExtra( name = "xxlarge-text-left" )
	String xxlargeTextLeft();

	@HexaCssExtra( name = "xxlarge-text-right" )
	String xxlargeTextRight();
}