package fr.lteconsulting.hexa.client.ui.resources.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources extends ClientBundle
{
	public final static ImageResources INSTANCE = GWT.create( ImageResources.class );

	@Source( "16-em-cross.png" )
	ImageResource close();

	@Source( "edit.png" )
	ImageResource edit();

	@Source( "add.gif" )
	ImageResource add();

	@Source( "connect.png" )
	ImageResource connect();

	@Source( "disconnect.png" )
	ImageResource disconnect();

	@Source( "text.png" )
	ImageResource text();

	@Source( "userInfo.png" )
	ImageResource userInfo();

	@Source( "detail.png" )
	ImageResource detail();

	@Source( "client16.png" )
	ImageResource client16x16();

	@Source( "findClient32.png" )
	ImageResource findClient32x32();

	@Source( "findClient16.png" )
	ImageResource findClient16x16();

	@Source( "findLastClient32.png" )
	ImageResource findLastClient32x32();

	@Source( "order-history-icon.png" )
	ImageResource orderhistory32x32();

	@Source( "order16.png" )
	ImageResource order16x16();

	@Source( "order-search32.png" )
	ImageResource ordersearch32x32();

	@Source( "order-search16.png" )
	ImageResource ordersearch16x16();

	@Source( "order-create32.png" )
	ImageResource ordercreate32x32();

	@Source( "order-create16.png" )
	ImageResource ordercreate16x16();

	@Source( "return-create32.png" )
	ImageResource returncreate32x32();

	@Source( "return-create16.png" )
	ImageResource returncreate16x16();

	@Source( "phenixbg.png" )
	ImageResource appbackground();

	@Source( "sync32.png" )
	ImageResource sync32x32();

	@Source( "sync32_2.png" )
	ImageResource sync2_32x32();

	@Source( "user32.png" )
	ImageResource user32x32();

	@Source( "user16.png" )
	ImageResource user16x16();

	@Source( "secteur32.png" )
	ImageResource secteur32x32();

	@Source( "secteur16.png" )
	ImageResource secteur16x16();

	@Source( "quotas-view32.png" )
	ImageResource quotasview32x32();

	@Source( "quota-edit32.png" )
	ImageResource quotasedit32x32();

	@Source( "quotas-view16.png" )
	ImageResource quotasview16x16();

	@Source( "quota-edit16.png" )
	ImageResource quotasedit16x16();

	@Source( "logout32.png" )
	ImageResource logout32x32();

	@Source( "visualiser.png" )
	ImageResource visu16x16();

	@Source( "localstorage16.png" )
	ImageResource localStorageActive16x16();

	@Source( "localstorageerror16.png" )
	ImageResource localStorageInactive16x16();

	@Source( "catalogue-create32.png" )
	ImageResource cataloguecreate32x32();

	@Source( "catalogue-create16.png" )
	ImageResource cataloguecreate16x16();

	@Source( "catalogue-search32.png" )
	ImageResource cataloguesearch32x32();

	@Source( "catalogue-search16.png" )
	ImageResource cataloguesearch16x16();

	@Source( "catalogue16.png" )
	ImageResource catalogue16x16();

	@Source( "next24.png" )
	ImageResource next24x24();

	@Source( "prev24.png" )
	ImageResource prev24x24();

	@Source( "logout16.png" )
	ImageResource logout16();

	@Source( "sync16.png" )
	ImageResource sync16();

	@Source( "reference32.png" )
	ImageResource reference32x32();

	@Source( "reference16.png" )
	ImageResource reference16x16();

	@Source( "key16.png" )
	ImageResource key16x16();

	@Source( "info16.png" )
	ImageResource info16x16();

	@Source( "lock16.png" )
	ImageResource lock16x16();

	@Source( "loading16.gif" )
	ImageResource loading16x16();

	@Source( "new24.png" )
	ImageResource new24x24();

	@Source( "new16.png" )
	ImageResource new16x16();

	@Source( "reset16.png" )
	ImageResource reset16();

	@Source( "cancel16.png" )
	ImageResource cancel16();

	@Source( "save16.png" )
	ImageResource save16();

	@Source( "valid16.png" )
	ImageResource valid16();

	@Source( "visualiser.png" )
	ImageResource search16();

	@Source( "create16.png" )
	ImageResource create16();

	@Source( "configure16.png" )
	ImageResource configure16();

	@Source( "delete16.png" )
	ImageResource delete16();

	@Source( "alert16.png" )
	ImageResource alert16x16();

	@Source( "folder_open16.png" )
	ImageResource folderOpen16x16();

	@Source( "folder_close16.png" )
	ImageResource folderClose16x16();

	@Source( "article16.png" )
	ImageResource article16x16();

	@Source( "trans16.png" )
	ImageResource trans16();

	@Source( "duplicate16.png" )
	ImageResource duplicate16();

	@Source( "exportOrder16.png" )
	ImageResource exportOrder16();

	@Source( "exportPost16.png" )
	ImageResource exportPost16();

	@Source( "copy16.png" )
	ImageResource copy16();

	@Source( "rename16.png" )
	ImageResource rename16();

	@Source( "print16.png" )
	ImageResource print16();

	@Source( "online16.png" )
	ImageResource online16();

	@Source( "offline16.png" )
	ImageResource offline16();

	@Source( "online32.png" )
	ImageResource online32();

	@Source( "offline32.png" )
	ImageResource offline32();

	@Source( "offlineInfo32.png" )
	ImageResource offlineInfo32();

	@Source( "offlineInfo16.png" )
	ImageResource offlineInfo16();
}
