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
public class TomcatStart extends EmbeddedTomcat {

    public static String WEBAPP_SRC = "src/main/webapp";

    protected TomcatStart() {

    }

    @Override
    protected WebArchive createWebArchive() {
        final WebArchive archive = ShrinkWrap.create(WebArchive.class, "mediaserver.war");
        archive.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
                .addPackage(test.class.getPackage())
                .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/templates/server.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/layout/contentTemplate.xhtml"), "xhtml/layout/contentTemplate.xhtml")
                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/layout/headerTemplate.xhtml"), "xhtml/layout/headerTemplate.xhtml")
                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/layout/footerTemplate.xhtml"), "xhtml/layout/footerTemplate.xhtml")
                .addAsWebResource(new File(WEBAPP_SRC, "xhtml/layout/mainTemplate.xhtml"), "xhtml/layout/mainTemplate.xhtml")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/css/main.css"), "resources/css/main.css")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/css/main_ie.css"), "resources/css/main_ie.css")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/css/connexion.css"), "resources/css/connexion.css")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/css/icone.css"), "resources/css/icone.css")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/js/main.js"), "resources/js/main.js")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/js/app.js"), "resources/js/app.js")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/images/icones/livre.gif"), "resources/images/icones/livre.gif")
                .addAsWebResource(new File(WEBAPP_SRC, "resources/images/icones/aide.gif"), "resources/images/icones/aide.gif")
                
                ;

        return archive;
    }

    @Override
    protected String getApplicationId() {
        return "mediaserver";
    }

}
