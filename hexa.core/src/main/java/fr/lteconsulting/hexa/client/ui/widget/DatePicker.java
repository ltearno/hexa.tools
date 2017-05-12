package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.calendar.Calendar;
import fr.lteconsulting.hexa.client.calendar.CalendarPeriod;
import fr.lteconsulting.hexa.client.calendar.PeriodBoundaries;
import fr.lteconsulting.hexa.client.calendar.Tree;
import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.css.HexaCss;

public class DatePicker extends Widget implements HasValueChangeHandlers<HexaDate>
{
	public final static Css CSS = GWT.create( Css.class );

	public interface Css extends HexaCss
	{
		String hexaDatePicker();

		String previousMonth();

		String nextMonth();

		String selectable();

		String unavailable();

		String headerRow();

		String row();

		String today();

		String button();
	}

	// TODO
	// Localization of day names and start of the week day
	Tree available = null;

	HexaDate currentlyDisplayedMonth;

	public void setAvailablePeriod( String availablePeriod )
	{
		this.available = Calendar.get().Parse( availablePeriod );

		PeriodBoundaries boundaries = new PeriodBoundaries();
		available.getFlat().GetBoundaries( boundaries );

		setCurrentMonth( new HexaDate( boundaries.from ) );
	}

	public void setCurrentMonth( HexaDate selectedMonth )
	{
		if( selectedMonth == null || !selectedMonth.isValid() )
			selectedMonth = new HexaDate();

		CalendarPeriod availablePeriods = available != null ? available.getFlat() : Calendar.get().Parse( "a" ).getFlat();

		int today = new HexaDate().toInt();

		HexaDate firstDayOfTheMonth = new HexaDate( selectedMonth.getYear(), selectedMonth.getMonth(), 1 );

		currentlyDisplayedMonth = firstDayOfTheMonth;

		int shift = -nativeToEuropean( firstDayOfTheMonth.getDay() );
		HexaDate firstDisplayedDay = firstDayOfTheMonth.addDays( shift );

		HexaDate currentDay = new HexaDate( firstDisplayedDay.getYear(), firstDisplayedDay.getMonth(), firstDisplayedDay.getDate() );

		StringBuilder sb = new StringBuilder();

		sb.append( "<div class='" + CSS.headerRow() + "'><button class='" + CSS.previousMonth() + " " + CSS.button() + "'>&lt;</button>" );
		sb.append( "<div>" + HexaDate.MonthNames[selectedMonth.getMonth()] + " " + selectedMonth.getHumanYear() + "</div>" );
		sb.append( "<button class='" + CSS.nextMonth() + " " + CSS.button() + "'>&gt;</button></div>" );

		sb.append( "<div class='" + CSS.row() + "'>" );
		for( int i = 0; i < 7; i++ )
			sb.append( "<div>" + HexaDate.DayNames[europeanToNative( i )].substring( 0, 1 ) + "</div>" );
		sb.append( "</div>" );

		for( int r = 0; r < 6; r++ )
		{
			sb.append( "<div class='" + CSS.row() + "'>" );
			for( int i = 0; i < 7; i++ )
			{
				boolean isDayAvailable = availablePeriods.IsInside( currentDay.getString() );

				String className = isDayAvailable ? CSS.selectable() : CSS.unavailable();
				if( today == currentDay.toInt() )
					className += " " + CSS.today();
				sb.append( "<div class='" + className + "'" + (isDayAvailable ? " data='" + currentDay.toInt() + "'" : "") + ">" + currentDay.getDate() + "</div>" );

				currentDay = currentDay.addDays( 1 );
			}
			sb.append( "</div>" );
		}

		getElement().setInnerHTML( sb.toString() );
	}

	public DatePicker()
	{
		DivElement root = Document.get().createDivElement();

		setElement( root );
		setStylePrimaryName( CSS.hexaDatePicker() );

		setCurrentMonth( new HexaDate() );

		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				Element target = Element.as( event.getNativeEvent().getEventTarget() );
				while( target != null && target != getElement() )
				{
					String data = target.getAttribute( "data" );
					if( data != null && !data.isEmpty() )
					{
						HexaDate date = new HexaDate( Integer.parseInt( data ) );

						ValueChangeEvent.fire( DatePicker.this, date );

						return;
					}
					else if( target.hasClassName( CSS.previousMonth() ) )
					{
						setCurrentMonth( currentlyDisplayedMonth.addDays( -5 ) );
						return;
					}
					else if( target.hasClassName( CSS.nextMonth() ) )
					{
						int nbDaysInMonth = HexaDate.GetDaysInMonth( currentlyDisplayedMonth.getYear(), currentlyDisplayedMonth.getMonth() );

						setCurrentMonth( currentlyDisplayedMonth.addDays( nbDaysInMonth ) );
						return;
					}

					target = target.getParentElement();
				}
			}
		}, ClickEvent.getType() );
	}

	private int nativeToEuropean( int day )
	{
		return (day + 6) % 7;
	}

	private int europeanToNative( int day )
	{
		return (day + 1) % 7;
	}

	@Override
	public HandlerRegistration addValueChangeHandler( ValueChangeHandler<HexaDate> handler )
	{
		return addHandler( handler, ValueChangeEvent.getType() );
	}
}
