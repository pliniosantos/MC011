/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import java.util.*;
import minijava.analysis.*;

@SuppressWarnings("nls")
public final class ABlockStatement extends PStatement
{
    private TLBrace _token_;
    private final LinkedList<PStatement> _statements_ = new LinkedList<PStatement>();

    public ABlockStatement()
    {
        // Constructor
    }

    public ABlockStatement(
        @SuppressWarnings("hiding") TLBrace _token_,
        @SuppressWarnings("hiding") List<PStatement> _statements_)
    {
        // Constructor
        setToken(_token_);

        setStatements(_statements_);

    }

    @Override
    public Object clone()
    {
        return new ABlockStatement(
            cloneNode(this._token_),
            cloneList(this._statements_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABlockStatement(this);
    }

    public TLBrace getToken()
    {
        return this._token_;
    }

    public void setToken(TLBrace node)
    {
        if(this._token_ != null)
        {
            this._token_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._token_ = node;
    }

    public LinkedList<PStatement> getStatements()
    {
        return this._statements_;
    }

    public void setStatements(List<PStatement> list)
    {
        this._statements_.clear();
        this._statements_.addAll(list);
        for(PStatement e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._token_)
            + toString(this._statements_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._token_ == child)
        {
            this._token_ = null;
            return;
        }

        if(this._statements_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._token_ == oldChild)
        {
            setToken((TLBrace) newChild);
            return;
        }

        for(ListIterator<PStatement> i = this._statements_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStatement) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
