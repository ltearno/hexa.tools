package fr.lteconsulting.hexa.client.ui.chart;

public class Referential
{
	public Chart chart;

	float originX = 0; // data point X located at the origin of the display
	float endX = 10; // data point X located at the end of the display

	float originY = 0;
	float endY = 10;

	public Referential( Chart chart )
	{
		this.chart = chart;
	}

	public void init( float originX, float originY, float endX, float endY )
	{
		this.originX = originX;
		this.originY = originY;

		this.endX = endX;
		this.endY = endY;
	}

	public float getRealX( float x )
	{
		// width = chart width - margins (left+right)
		int width = chart.width - chart.marginLeft - chart.marginRight;

		return chart.marginLeft + getReal( x, width, originX, endX );
	}

	public float getRealY( float y )
	{
		// height = chart height - margins (top+bottom)
		int height = chart.height - chart.marginTop - chart.marginBottom;

		return chart.height - chart.marginBottom - getReal( y, height, originY, endY );
	}

	public float getDataX( float x )
	{
		// width = chart width - margins (left+right)
		int width = chart.width - chart.marginLeft - chart.marginRight;

		return getData( x - chart.marginLeft, width, originX, endX );
	}

	public float getMinY()
	{
		return originY;
	}

	public void setMinY( float min )
	{
		originY = min;
	}

	public float getMaxY()
	{
		return endY;
	}

	public void setMaxY( float max )
	{
		endY = max;
	}

	public float getMinX()
	{
		return originX;
	}

	public float getMaxX()
	{
		return endX;
	}

	private float getReal( float value, int pixelSize, float origin, float end )
	{
		return (pixelSize * (value - origin)) / (end - origin);
	}

	private float getData( float pixel, int pixelSize, float origin, float end )
	{
		return (pixel * (end - origin)) / pixelSize + origin;
	}
}
