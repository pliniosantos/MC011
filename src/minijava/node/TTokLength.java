/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import minijava.analysis.*;

@SuppressWarnings("nls")
public final class TTokLength extends Token
{
    public TTokLength()
    {
        super.setText("length");
    }

    public TTokLength(int line, int pos)
    {
        super.setText("length");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TTokLength(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTTokLength(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TTokLength text.");
    }
}