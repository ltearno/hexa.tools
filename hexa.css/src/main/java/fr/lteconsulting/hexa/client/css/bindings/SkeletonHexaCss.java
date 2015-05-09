package fr.lteconsulting.hexa.client.css.bindings;

import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

/**
 * An HexaCss interface to wrap Skeleton css classes.
 * 
 * All classes of the Skeleton should be wrapped here. If some are missing, feel
 * free to submit a pull request !
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 */
public interface SkeletonHexaCss extends HexaCss
{
	public static final SkeletonHexaCss CSS = GWT.create( SkeletonHexaCss.class );
	
	@HexaCssExtra( name = "container" )
	String container();

	@HexaCssExtra( name = "column" )
	String column();

	@HexaCssExtra( name = "columns" )
	String columns();

	@HexaCssExtra( name = "one.column" )
	String oneColumn();

	@HexaCssExtra( name = "one.columns" )
	String oneColumns();

	@HexaCssExtra( name = "two.columns" )
	String twoColumns();

	@HexaCssExtra( name = "three.columns" )
	String threeColumns();

	@HexaCssExtra( name = "four.columns" )
	String fourColumns();

	@HexaCssExtra( name = "five.columns" )
	String fiveColumns();

	@HexaCssExtra( name = "six.columns" )
	String sixColumns();

	@HexaCssExtra( name = "seven.columns" )
	String sevenColumns();

	@HexaCssExtra( name = "eight.columns" )
	String eightColumns();

	@HexaCssExtra( name = "nine.columns" )
	String nineColumns();

	@HexaCssExtra( name = "ten.columns" )
	String tenColumns();

	@HexaCssExtra( name = "eleven.columns" )
	String elevenColumns();

	@HexaCssExtra( name = "twelve.columns" )
	String twelveColumns();

	@HexaCssExtra( name = "one-third.column" )
	String oneThirdColumn();

	@HexaCssExtra( name = "two-thirds.column" )
	String twoThirdsColumn();

	@HexaCssExtra( name = "one-half.column" )
	String oneHalfColumn();

	@HexaCssExtra( name = "offset-by-one.column" )
	String offsetByOneColumn();

	@HexaCssExtra( name = "offset-by-one.columns" )
	String offsetByOneColumns();

	@HexaCssExtra( name = "offset-by-two.column" )
	String offsetByTwoColumn();

	@HexaCssExtra( name = "offset-by-two.columns" )
	String offsetByTwoColumns();

	@HexaCssExtra( name = "offset-by-three.column" )
	String offsetByThreeColumn();

	@HexaCssExtra( name = "offset-by-three.columns" )
	String offsetByThreeColumns();

	@HexaCssExtra( name = "offset-by-four.column" )
	String offsetByFourColumn();

	@HexaCssExtra( name = "offset-by-four.columns" )
	String offsetByFourColumns();

	@HexaCssExtra( name = "offset-by-five.column" )
	String offsetByFiveColumn();

	@HexaCssExtra( name = "offset-by-five.columns" )
	String offsetByFiveColumns();

	@HexaCssExtra( name = "offset-by-six.column" )
	String offsetBySixColumn();

	@HexaCssExtra( name = "offset-by-six.columns" )
	String offsetBySixColumns();

	@HexaCssExtra( name = "offset-by-seven.column" )
	String offsetBySevenColumn();

	@HexaCssExtra( name = "offset-by-seven.columns" )
	String offsetBySevenColumns();

	@HexaCssExtra( name = "offset-by-eight.column" )
	String offsetByEightColumn();

	@HexaCssExtra( name = "offset-by-eight.columns" )
	String offsetByEightColumns();

	@HexaCssExtra( name = "offset-by-nine.column" )
	String offsetByNineColumn();

	@HexaCssExtra( name = "offset-by-nine.columns" )
	String offsetByNineColumns();

	@HexaCssExtra( name = "offset-by-ten.column" )
	String offsetByTenColumn();

	@HexaCssExtra( name = "offset-by-ten.columns" )
	String offsetByTenColumns();

	@HexaCssExtra( name = "offset-by-eleven.column" )
	String offsetByElevenColumn();

	@HexaCssExtra( name = "offset-by-eleven.columns" )
	String offsetByElevenColumns();

	@HexaCssExtra( name = "offset-by-one-third.column" )
	String offsetByOneThirdColumn();

	@HexaCssExtra( name = "offset-by-one-third.columns" )
	String offsetByOneThirdColumns();

	@HexaCssExtra( name = "offset-by-two-thirds.column" )
	String offsetByTwoThirdsColumn();

	@HexaCssExtra( name = "offset-by-two-thirds.columns" )
	String offsetByTwoThirdsColumns();

	@HexaCssExtra( name = "offset-by-one-half.column" )
	String offsetByOneHalfColumn();

	@HexaCssExtra( name = "offset-by-one-half.columns" )
	String offsetByOneHalfColumns();

	@HexaCssExtra( name = "button" )
	String button();

	@HexaCssExtra( name = "button-primary" )
	String buttonPrimary();

	@HexaCssExtra( name = "u-full-width" )
	String uFullWidth();

	@HexaCssExtra( name = "u-max-full-width" )
	String uMaxFullWidth();

	@HexaCssExtra( name = "u-pull-right" )
	String uPullRight();

	@HexaCssExtra( name = "u-pull-left" )
	String uPullLeft();

	@HexaCssExtra( name = "row" )
	String row();

	@HexaCssExtra( name = "u-cf" )
	String uCf();
}
