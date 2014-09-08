/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.red5;

import com.ihm.html.test;
import java.io.File;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 *
 * @author olivier
 */
public class TomcatStart extends EmbeddedTomcat{

    public static String WEBAPP_SRC="src/main/webapp";
    
    protected TomcatStart(){
        
    }
    @Override
    protected WebArchive createWebArchive() {
        final WebArchive archive = ShrinkWrap.create(WebArchive.class, "mediaserver.war");
		archive.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
				.addPackage(test.class.getPackage())
				.addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/templates/server.xhtml"));
				/*.addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/listStudents.xhtml"), "xhtml/listStudents.xhtml")
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/index.xhtml"), "xhtml/index.xhtml")
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/registerStudent.xhtml"), "xhtml/registerStudent.xhtml")
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/templates/layout.xhtml"), "xhtml/templates/layout.xhtml")
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/templates/searchbox.xhtml"),
						"xhtml/templates/searchbox.xhtml")
				.addAsWebResource(new File(WEBAPP_SRC, "xhtml/templates/dialog.xhtml"), "xhtml/templates/dialog.xhtml");
                                */
		return archive;
    }

    @Override
    protected String getApplicationId() {
        return "mediaserver";
    }
    
}
