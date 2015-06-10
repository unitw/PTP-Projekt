/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import javax.xml.stream.XMLEventFactory;

/**
 *
 * @author win7
 */
public interface StaxStore {

    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory);

    public String getIdentifier();

    public void setIdentifier(String s);

    public int getXpos();

    public void setXpos(String s);

    public int getYpos();

    public void setYpos(String s);

    public int getbreite();

    public void setbreite(String s);

    public int gethoehe();

    public void sethoehe(String s);
}
