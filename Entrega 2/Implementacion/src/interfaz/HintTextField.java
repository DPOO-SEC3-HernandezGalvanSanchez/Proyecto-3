/*
 * ESTE PROGRAMA FUE EXTRAIDO DE
 * https:// www.iteramos.com/pregunta/84696/java-jtextfield-con-pista-de-entrada
 */

package interfaz;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
class HintTextField extends JTextField implements FocusListener
{ 
	private final String hint;
	private boolean showingHint;
	
	
	public HintTextField(final String hint)
	{
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		setForeground(Color.GRAY); 
		super.addFocusListener(this);
	}
	
	@Override
	public void focusGained(FocusEvent e)
	{
		if(this.getText().isEmpty())
		{
			super.setText("");
			setForeground(Color.BLACK); 
			showingHint = false;
		}
	}
	
	@Override
	public void focusLost(FocusEvent e)
	{
		if(this.getText().isEmpty())
		{
			super.setText(hint);
			setForeground(Color.GRAY); 
			showingHint = true;
		}
	}
	
	@Override
	public String getText()
	{
		return showingHint ? "" : super.getText();
	}
	
}

