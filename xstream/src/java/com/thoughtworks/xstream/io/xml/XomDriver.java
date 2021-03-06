/*
 * Copyright (C) 2006 Joe Walnes.
 * Copyright (C) 2006, 2007, 2009, 2011, 2014 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 12. April 2006 by Joerg Schaible
 */
package com.thoughtworks.xstream.io.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.naming.NameCoder;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


public class XomDriver extends AbstractXmlDriver {

    private final Builder builder;

    public XomDriver() {
        this(new Builder());
    }

    public XomDriver(final Builder builder) {
        this(builder, new XmlFriendlyNameCoder());
    }

    /**
     * @since 1.4
     */
    public XomDriver(final NameCoder nameCoder) {
        this(new Builder(), nameCoder);
    }

    /**
     * @since 1.4
     */
    public XomDriver(final Builder builder, final NameCoder nameCoder) {
        super(nameCoder);
        this.builder = builder;
    }

    /**
     * @since 1.2
     * @deprecated As of 1.4, use {@link #XomDriver(Builder, NameCoder)} instead
     */
    @Deprecated
    public XomDriver(final XmlFriendlyReplacer replacer) {
        this(new Builder(), replacer);
    }

    /**
     * @since 1.2
     * @deprecated As of 1.4, use {@link #XomDriver(Builder, NameCoder)} instead
     */
    @Deprecated
    public XomDriver(final Builder builder, final XmlFriendlyReplacer replacer) {
        this((NameCoder)replacer);
    }

    protected Builder getBuilder() {
        return builder;
    }

    @Override
    public HierarchicalStreamReader createReader(final Reader text) {
        try {
            final Document document = getBuilder().build(text);
            return new XomReader(document, getNameCoder());
        } catch (final ValidityException e) {
            throw new StreamException(e);
        } catch (final ParsingException e) {
            throw new StreamException(e);
        } catch (final IOException e) {
            throw new StreamException(e);
        }
    }

    @Override
    public HierarchicalStreamReader createReader(final InputStream in) {
        try {
            final Document document = getBuilder().build(in);
            return new XomReader(document, getNameCoder());
        } catch (final ValidityException e) {
            throw new StreamException(e);
        } catch (final ParsingException e) {
            throw new StreamException(e);
        } catch (final IOException e) {
            throw new StreamException(e);
        }
    }

    @Override
    public HierarchicalStreamReader createReader(final URL in) {
        try {
            final Document document = getBuilder().build(in.toExternalForm());
            return new XomReader(document, getNameCoder());
        } catch (final ValidityException e) {
            throw new StreamException(e);
        } catch (final ParsingException e) {
            throw new StreamException(e);
        } catch (final IOException e) {
            throw new StreamException(e);
        }
    }

    @Override
    public HierarchicalStreamReader createReader(final File in) {
        try {
            final Document document = getBuilder().build(in);
            return new XomReader(document, getNameCoder());
        } catch (final ValidityException e) {
            throw new StreamException(e);
        } catch (final ParsingException e) {
            throw new StreamException(e);
        } catch (final IOException e) {
            throw new StreamException(e);
        }
    }

    @Override
    public HierarchicalStreamWriter createWriter(final Writer out) {
        return new PrettyPrintWriter(out, getNameCoder());
    }

    @Override
    public HierarchicalStreamWriter createWriter(final OutputStream out) {
        return new PrettyPrintWriter(new OutputStreamWriter(out), getNameCoder());
    }
}
