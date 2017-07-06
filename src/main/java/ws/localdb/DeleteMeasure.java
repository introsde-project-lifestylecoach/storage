
package ws.localdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per deleteMeasure complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="deleteMeasure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measureId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteMeasure", propOrder = {
    "measureId"
})
public class DeleteMeasure {

    protected int measureId;

    /**
     * Recupera il valore della proprietà measureId.
     * 
     */
    public int getMeasureId() {
        return measureId;
    }

    /**
     * Imposta il valore della proprietà measureId.
     * 
     */
    public void setMeasureId(int value) {
        this.measureId = value;
    }

}
