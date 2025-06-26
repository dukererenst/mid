/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;



import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.primefaces.PrimeFaces;



/**
 *
 * @author Ernest
 */
public class JSF {

   
   public static void addSuccessMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
   }
   
   public static void addErrorMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
   }
   
   public static void addWarningMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
   }
   
   public static void addDynamicMessage(String message, boolean type) {
      if (type) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
      } else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
      }
   }
   
   public static void removeSessionMapValue(String key) {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
   }
   
   public static Object getRequestMapValue(String key) {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
   }
   
   public static void setRequestMapValue(String key, Object value) {
      FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
   }
   
   public static Object getSessionMapValue(String key) {
      return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
   }
   
   public static void setSessionMapValue(String key, Object value) {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
      
   }
   
   public static HttpServletRequest getHttpServletRequest() {
      return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
   }
   
   public static HttpServletResponse getHttpServletResponse() {
      return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
   }
   
   public static HttpServletRequest httpRequest() {
      return getHttpServletRequest();
   }
   
   public static HttpServletResponse httpResponse() {
      return getHttpServletResponse();
   }
   
   public static HttpServletRequest request() {
      return getHttpServletRequest();
   }
   
   public static HttpServletResponse response() {
      return getHttpServletResponse();
   }
   
   public static String getApplicationUri() {
      try {
         FacesContext ctxt = FacesContext.getCurrentInstance();
         ExternalContext ext = ctxt.getExternalContext();
         URI uri = new URI(
            ext.getRequestScheme(),
            null,
            ext.getRequestServerName(),
            ext.getRequestServerPort(),
            ext.getRequestContextPath(),
            null,
            null);
         return uri.toASCIIString();
      } catch (URISyntaxException e) {
         throw new FacesException(e);
      }
   }
   
   public static void successMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
   }
   
   public static void errorMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
   }
   
   public static void warningMessage(String message) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
   }
   
   public static void message(String summary, String detail, Severity severity) {
      summary = summary == null ? "" : summary;
      detail = detail == null ? "" : detail;
      if (severity != null) {
         FacesMessage message = new FacesMessage(severity, summary, detail);
         facesContext().addMessage(null, message);
      }
   }
   
   public static void message(String text, Severity severity) {
      message(text, text, severity);
   }
   
   public static void info(String title, String message) {
      message(title, message, FacesMessage.SEVERITY_INFO);
   }
   
   public static void warning(String title, String message) {
      message(title, message, FacesMessage.SEVERITY_WARN);
   }
   
   public static void error(String title, String message) {
      message(title, message, FacesMessage.SEVERITY_ERROR);
   }
   
   public static void fatal(String title, String message) {
      message(title, message, FacesMessage.SEVERITY_FATAL);
   }
   
   public static void info(String message) {
      message(message, FacesMessage.SEVERITY_INFO);
   }
   
   public static void warning(String message) {
      message(message, FacesMessage.SEVERITY_WARN);
   }
   
   public static void error(String message) {
      message(message, FacesMessage.SEVERITY_ERROR);
   }
   
   public static void fatal(String message) {
      message(message, FacesMessage.SEVERITY_FATAL);
   }
   
   /**
    * Classe interna responsável por agrupar os métodos relacionados
    * a apresentação de mensagens.
    * 
    * @author thiago-amm
    */
   public static final class message {
      
      public static void info(String message) {
         message(message, FacesMessage.SEVERITY_INFO);
      }
      
      public static void warning(String message) {
         message(message, FacesMessage.SEVERITY_WARN);
      }
      
      public static void error(String message) {
         message(message, FacesMessage.SEVERITY_ERROR);
      }
      
      public static void fatal(String message) {
         message(message, FacesMessage.SEVERITY_FATAL);
      }
      
   }
   
//   public static FacesContext getRequestContext() {
//      return PrimeFacesContext.getCurrentInstance();
//   }
   
   public static PrimeFaces requestContext() {
      return PrimeFaces.current();
   }
   
   public static void execute(String script) {
      requestContext().executeScript(script);
   }
   
   public static void update(String name) {
      requestContext().executeScript(name);
   }
   
   public static FacesContext getFacesContext() {
      return FacesContext.getCurrentInstance();
   }
   
   public static FacesContext facesContext() {
      return getFacesContext();
   }
   
   public static ExternalContext getExternalContext() {
      return facesContext().getExternalContext();
   }
   
   public static ExternalContext externalContext() {
      return getExternalContext();
   }
   
   public static void redirect(String url) {
      try {
         externalContext().redirect(url);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void forward(String url) {
      try {
         externalContext().dispatch(url);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void invalidateSession() {
      externalContext().invalidateSession();
   }
   
   public static HttpSession getHttpSession(boolean create) {
      HttpSession session = (HttpSession) externalContext().getSession(create);
      return session;
   }
   
   public static HttpSession getHttpSession() {
      return getHttpSession(false);
   }
   
   public static HttpSession httpSession(boolean create) {
      return getHttpSession(create);
   }
   
   public static HttpSession httpSession() {
      return httpSession(false);
   }
   
   public static Map<String, String> params() {
      return externalContext().getRequestParameterMap();
   }
   
   public static ServletContext getServletContext() {
      return httpSession().getServletContext();
   }
   
   public static ServletContext getContext(String name) {
      return getServletContext().getContext(name);
   }
   
   public static ServletContext context(String name) {
      return getContext(name);
   }
   
   public static ServletContext servletContext() {
      return getServletContext();
   }
   
   public static String getContextName() {
      return externalContext().getContextName();
   }
   
   public static String contextName() {
      return externalContext().getContextName();
   }
   
  
   public static String getRemoteUser() {
      return externalContext().getRemoteUser();
   }
   
   public static String remoteUser() {
      return getRemoteUser();
   }
   
   public static String getContextURL() {
      try {
         String scheme = externalContext().getRequestScheme();
         String host = externalContext().getRequestServerName();
         int port = externalContext().getRequestServerPort();
         String path = externalContext().getRequestContextPath();
         URI uri = new URI(scheme, null, host, port, path, null, null);
         return uri.toASCIIString();
      } catch (URISyntaxException e) {
         throw new FacesException(e);
      }
   }
   
 

  
   // http://localhost:8080/sep/admin/sorteio/
   public static String getRequestURL() {
      return JSF.request().getRequestURL().toString();
   }
   
   public static String requestURL() {
      return getRequestURL();
   }
   
   // /sep/admin/sorteio/
   public static String getRequestURI() {
      return JSF.request().getRequestURI().toString();
   }
   
   public static String requestURI() {
      return getRequestURI();
   } 
}
