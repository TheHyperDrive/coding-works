/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.bl;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author micha
 */
public class ReloadPage {
    
    public static void reloadPage(FacesContext context) {
        String refreshpage = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, refreshpage);
        root.setViewId(refreshpage);
        context.setViewRoot(root);
    }
}
