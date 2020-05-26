/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package coffeepot.br.sintegra.writer;

/*
 * #%L
 * coffeepot-br-sintegra
 * %%
 * Copyright (C) 2013 Jeandeson O. Merelis
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import coffeepot.bean.wr.writer.ObjectWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class SintegraWriter {

    private final ObjectWriter beanWriter;

    public SintegraWriter(Writer w) {
        this.beanWriter = WriterFactory.createObjectWriter(w);
    }

    public Writer getWriter() {
        return beanWriter.getWriter();
    }

    public void setWriter(Writer w) {
        beanWriter.setWriter(w);
    }

    /**
     * Escreve o objeto no arquivo, obedecendo a formatação que foi mapeada na classe.
     *
     * @param obj Objeto a ser analizado e escrito no arquivo. Este objeto deve ser mapeado com as anotações {@link coffeepot.bean.wr.annotation.Record} e
     * {@link coffeepot.bean.wr.annotation.Field}
     * @throws IOException
     */
    public void write(Object obj) throws IOException {
        beanWriter.write(obj);
    }

    /**
     * Escreve os registros que estão em buffer para o destino.
     *
     * @throws IOException
     */
    public void flush() throws IOException {
        beanWriter.flush();
    }

    /**
     * Fecha o escritor.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        beanWriter.close();
    }
}
