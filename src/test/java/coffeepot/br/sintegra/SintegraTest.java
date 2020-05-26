/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package coffeepot.br.sintegra;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import coffeepot.br.sintegra.registros.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

import coffeepot.br.sintegra.tipos.Convenio;
import coffeepot.br.sintegra.tipos.DocumentoFiscal;
import coffeepot.br.sintegra.tipos.Emitente;
import coffeepot.br.sintegra.tipos.FinalidadeArquivo;
import coffeepot.br.sintegra.tipos.NaturezaOperacao;
import coffeepot.br.sintegra.tipos.SituacaoDocumentoFiscal;
import coffeepot.br.sintegra.writer.SintegraWriter;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class SintegraTest {

	public SintegraTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	private Date converterData(String valor) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();
		try {
			data = df.parse(valor);
		} catch (ParseException ex) {
			ex.printStackTrace();

		}
		return data;
	}

	/**
	 * Please, alguém faça uma série de testes decente.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSintegra() throws Exception {
		// Registro Mestre do Estabelecimento
		Registro10 r10 = new Registro10();
		r10.setCnpj("26957858000132");
		r10.setIe("138009252");
		r10.setRazaoSocial("WILLIAN RESPLANDES MATIAS");
		r10.setCidade("Peixoto de Azevedo");
		r10.setUf("MT");
		r10.setFax("6635752666");

		Date dataInicial = new Date();
		Date dataFinal = new Date();

		dataInicial = converterData("01/04/2020");
		dataFinal = converterData("30/04/2020");

		r10.setDataInicial(dataInicial);
		r10.setDataFinal(dataFinal);

		r10.setCodigoConvenio(Convenio.CONV_3_5795_7603);
		r10.setNaturezaOperacao(NaturezaOperacao.TOTALIDADE_DAS_OPERACOES);
		r10.setFinalidadeArquivo(FinalidadeArquivo.NORMAL);

		// Registo de dados complementares do estabelecimento
		Registro11 r11 = new Registro11();
		r11.setLogradouro("Avenida Brasil");
		r11.setNumero(436);
		r11.setComplemento("SoftJava Automacao");
		r11.setBairro("Centro");
		r11.setCep("78530000");
		r11.setResponsavel("Willian Resplandes Matias");
		r11.setTelefone("6699716730");

		// Registro de NFe de Entrada e Saida modelo 55
		List<Registro50> r50List = new ArrayList<>();

		Registro50 r50 = new Registro50();
		r50.setCpfCnpj("10658657000102");
		r50.setIe("133676560");

		Date dataEmissao = new Date();
		dataEmissao = converterData("05/04/2020");

		r50.setDataDocumento(dataEmissao);

		r50.setUf("MT");
		r50.setModeloDocumento(DocumentoFiscal.NOTA_FISCAL_ELETRONICA);
		r50.setSerieDocumento("1");
		r50.setNumeroDocumento(1L);
		r50.setCfop(5101);
		r50.setEmitente(Emitente.PROPRIO);
		r50.setValorTotal(10.00);
		r50.setBaseDeCalculoIcms(0.00);
		r50.setValorIcms(0.00);
		r50.setValorOutras(0.00);
		r50.setAliquotaIcms(0.0);
		r50.setSituacaoDocumento(SituacaoDocumentoFiscal.NORMAL);

		r50List.add(r50);

		Registro54 r54 = new Registro54();
		r54.setCpfCnpj("10658657000102");
		r54.setModeloDocumento(DocumentoFiscal.NOTA_FISCAL_ELETRONICA);
		r54.setSerieDocumento("1");
		r54.setNumeroDocumento(1);
		r54.setCfop(5101);
		r54.setCst("500");
		r54.setNumeroItem(1);
		r54.setCodigoProduto("1");
		r54.setQuantidade(1.00);
		r54.setValorBrutoItem(10.00);
		r54.setValorDesconto(0.00);
		r54.setBasedeCalculoIcms(0.00);
		r54.setBaseDeCalculoIcmsST(0.00);
		r54.setValorDesconto(0.00);
		r54.setValorIpi(0.00);
		r54.setAliquotaIcms(0.00);

		List<Registro54> r54List = new LinkedList<>();
		r54List.add(r54);

//
//        r50 = new Registro50();
//        r50.setAliquotaIcms(18.0);
//        r50.setCfop(5101);
//        r50.setCpfCnpj("12-15161-ad541001");
//        r50.setDataDocumento(new Date());
//        r50.setEmitente(Emitente.PROPRIO);
//        r50.setIe("mais ié");
//        r50.setModeloDocumento(DocumentoFiscal.CONHECIMENTO_TRANSPORTE_FERROVIARIO_CARGAS);
//        r50.setNumeroDocumento(1L);
//        r50.setSerieDocumento("1");
//        r50.setSituacaoDocumento(SituacaoDocumentoFiscal.CANCELADO);
//        r50.setUf("ES");
//        r50.setValorIcms(1222.057);
//        r50.setValorTotal(12151.55);
//
//        r50List.add(r50);

//        List<Registro51> r51List = new ArrayList<>();
//
//        Registro51 r51 = new Registro51();
//        r51.setCfop(5101);
//        r51.setCpfCnpj("121+1515113,51");
//        r51.setDataDocumento(r50.getDataDocumento());
//        r51.setIe("adfdafadfd fadsf 5411616161 a61fd6 adf");
//        r51.setNumeroDocumento(999999);
//        r51.setSerieDocumento("sssss");
//        r51.setSituacaoDocumento(SituacaoDocumentoFiscal.CANCELADO);
//        r51.setUf("UUU");
//        r51.setValorTotal(1555d);
//        r51.setValorIpi(111111.111);
//        r51List.add(r51);
//
//        r51 = new Registro51();
//        r51.setCfop(5101);
//        r51.setCpfCnpj("121+1515113,51");
//        r51.setDataDocumento(r50.getDataDocumento());
//        r51.setIe("adfdafadfd fadsf 5411616161 a61fd6 adf");
//        r51.setNumeroDocumento(999999);
//        r51.setSerieDocumento("sssss");
//        r51.setSituacaoDocumento(SituacaoDocumentoFiscal.CANCELADO);
//        r51.setUf("UUU");
//        r51.setValorTotal(1555d);
//        r51.setValorIpi(111111.111);
//        r51List.add(r51);

        Registro53 r53 = new Registro53();
        r53.setBaseST(1111.99);
        r53.setCfop(5101);
        r53.setCnpj("06088741000403");
        r53.setDataDocumento(new Date());
        r53.setEmitente(Emitente.TERCEIROS);
        r53.setIe("IEIEIEIEIEIEIEIIEIE");
        r53.setModeloDocumento(DocumentoFiscal.NOTA_FISCAL_ELETRONICA);
        r53.setNumeroDocumento(3333333);
        r53.setSerieDocumento("SSSSS");
        r53.setSituacaoDocumento(SituacaoDocumentoFiscal.INUTILIZADO);
        r53.setUf("ES");

        List<Registro53> r53List = new LinkedList<>();
        r53List.add(r53);
//
//        Registro54 r54 = new Registro54();
//        r54.setAliquotaIcms(17d);
//        r54.setCfop(5101);
//        r54.setCodigoProduto("PPPPPPPPPPPPPPPPPP");
//        r54.setCpfCnpj("2222222--222222222222222-2");
//        r54.setCst("CST");
//        r54.setModeloDocumento(DocumentoFiscal.NOTA_FISCAL_ELETRONICA);
//        r54.setNumeroDocumento(33333333);
//        r54.setNumeroItem(1);
//        r54.setQuantidade(7.888);
//        r54.setSerieDocumento("SSS");
//        r54.setValorBrutoItem(19.22);
//        r54.setValorDesconto(4.44);
//        r54.setValorIpi(6.66);
//
//        List<Registro54> r54List = new LinkedList<>();
//        r54List.add(r54);

//        List<Registro60M> r60mList = new ArrayList<>();
//        List<Registro60A> r60aList = new ArrayList<>();
//        List<Registro60D> r60dList = new ArrayList<>();
//        List<Registro60I> r60iList = new ArrayList<>();

//        Registro60M r60m;
//        Registro60A r60a;
//        Registro60D r60d;
//        Registro60I r60i;
//
//
//        r60m = new Registro60M();
//        r60m.setCooInicial(111111);
//        r60m.setCooFinal(999999);
//        r60m.setCro(222);
//        r60m.setCrz(333333);
//        r60m.setDataEmissao(new Date());
//        r60m.setModeloDocumento("2C");
//        r60m.setNumeroOrdem(4);
//        r60m.setNumeroSerieEquipamento("serie");
//        r60m.setValorGT(5555.99);
//        r60m.setVendaBruta(6666.99);
//
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//
//        r60m.setRegistros60A(r60aList);
//        r60m.setRegistros60D(r60dList);
//        r60m.setRegistros60I(r60iList);
//        r60mList.add(r60m);
//
//        r60m = new Registro60M();
//        r60m.setCooInicial(111111);
//        r60m.setCooFinal(999999);
//        r60m.setCro(222);
//        r60m.setCrz(333333);
//        r60m.setDataEmissao(new Date());
//        r60m.setModeloDocumento("2C");
//        r60m.setNumeroOrdem(4);
//        r60m.setNumeroSerieEquipamento("serie");
//        r60m.setValorGT(5555.99);
//        r60m.setVendaBruta(6666.99);
//
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setModeloDocumento("MM");
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setModeloDocumento("MM");
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setModeloDocumento("MM");
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//
//        r60m.setRegistros60A(r60aList);
//        r60m.setRegistros60D(r60dList);
//        r60m.setRegistros60I(r60iList);
//        r60mList.add(r60m);
//
//        r60m = new Registro60M();
//        r60m.setCooInicial(111111);
//        r60m.setCooFinal(999999);
//        r60m.setCro(222);
//        r60m.setCrz(333333);
//        r60m.setDataEmissao(new Date());
//        r60m.setModeloDocumento("2C");
//        r60m.setNumeroOrdem(4);
//        r60m.setNumeroSerieEquipamento("serie");
//        r60m.setValorGT(5555.99);
//        r60m.setVendaBruta(6666.99);
//
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setModeloDocumento("MM");
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setModeloDocumento("MM");
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setModeloDocumento("MM");
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//
//        r60m.setRegistros60A(r60aList);
//        r60m.setRegistros60D(r60dList);
//        r60m.setRegistros60I(r60iList);
//        r60mList.add(r60m);
//
//        r60m = new Registro60M();
//        r60m.setCooInicial(111111);
//        r60m.setCooFinal(999999);
//        r60m.setCro(222);
//        r60m.setCrz(333333);
//        r60m.setDataEmissao(new Date());
//        r60m.setModeloDocumento("2C");
//        r60m.setNumeroOrdem(4);
//        r60m.setNumeroSerieEquipamento("serie");
//        r60m.setValorGT(5555.99);
//        r60m.setVendaBruta(6666.99);
//
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//        r60a = new Registro60A();
//        r60a.setDataEmissao(r60m.getDataEmissao());
//        r60a.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60a.setTotalizadorParcial("TOTALIZADOR");
//        r60a.setValorAcumulado(2222.99);
//        r60aList.add(r60a);
//
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//        r60d = new Registro60D();
//        r60d.setDataEmissao(r60m.getDataEmissao());
//        r60d.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60d.setTotalizadorParcial("TOTALIZADOR");
//        r60d.setBaseDeCalculoIcms(1111.99);
//        r60d.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60d.setQuantidade(2222.333);
//        r60d.setValorIcms(5555.99);
//        r60d.setValorLiquido(6666.99);
//        r60dList.add(r60d);
//
//        r60i = new Registro60I();
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60i.setModeloDocumento("MM");
//        r60iList.add(r60i);
//
//        r60i = new Registro60I();
//        r60i.setModeloDocumento("MM");
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//        r60i = new Registro60I();
//        r60i.setModeloDocumento("MM");
//        r60i.setDataEmissao(r60m.getDataEmissao());
//        r60i.setNumeroSerieEquipamento(r60m.getNumeroSerieEquipamento());
//        r60i.setTotalizadorParcial("TOTALIZADOR");
//        r60i.setBaseDeCalculoIcms(1111.99);
//        r60i.setCodigoProduto("PRODUTO-PRODUTO-PRODUTO");
//        r60i.setQuantidade(2222.333);
//        r60i.setValorIcms(5555.99);
//        r60i.setCoo(888888);
//        r60i.setNumeroItem(333);
//        r60i.setValorLiquido(6666.99);
//        r60iList.add(r60i);
//
//        r60m.setRegistros60A(r60aList);
//        r60m.setRegistros60D(r60dList);
//        r60m.setRegistros60I(r60iList);
//        r60mList.add(r60m);

		List<Registro61> r61List = new ArrayList<>();
		Registro61 r61;
		r61 = new Registro61();
		r61.setModeloDocumento(DocumentoFiscal.NOTA_FISCAL_VENDA_CONSUMIDOR);
		r61.setAliquotaIcms(0.00);
		r61.setDataEmissao(dataEmissao);
		r61.setNumeroOrdemInicial(1);
		r61.setNumeroOrdemFinal(1);
		r61.setSerieDocumento("D");
		//r61.setSubSerieDocumento("001");
		r61.setValorTotal(10.00);
		r61.setTotalBaseDeCalculoIcms(null);
		r61.setValorTotalIcms(0.00);
		r61.setValorTotalIsentas(0.00);
		r61.setValorTotalOutras(0.00);
		r61List.add(r61);


		List<Registro61R> r61RList = new ArrayList<>();
		Registro61R r61R;
		r61R = new Registro61R();
		r61R.setAliquotaIcms(0.00);
		r61R.setBaseDeCalculoIcms(0.00);
		r61R.setCodigoProduto("1");
		r61R.setMesAno("042020");
		r61R.setQuantidade(2.0);
		r61R.setValorBrutoProduto(20.00);
		r61RList.add(r61R);
		
		
		List<Registro75> r75List = new ArrayList<>();
		Registro75 r75;
		r75 =new Registro75();
		r75.setAliquotaIcms(0.00);
		r75.setAliquotaIpi(0.00);
		r75.setBaseCalculoIcmsST(0.00);
		r75.setCodigoProduto("1");
		r75.setDataInicial(dataInicial);
		r75.setDataFinal(dataFinal);
		r75.setDescricaoProduto("COCA-COLA LATA");
		r75.setNcm("22021000");
		r75.setPercentualReducaoBaseCalculoIcms(0.00);
		r75.setUnidade("UN");
		r75List.add(r75);
		
		
		Sintegra sintegra = new Sintegra();
		sintegra.setRegistro10(r10);
		sintegra.setRegistro11(r11);
		sintegra.setRegistros50(r50List);
		sintegra.setRegistros54(r54List);
		sintegra.setRegistros61(r61List);
		sintegra.setRegistros61R(r61RList);
		sintegra.setRegistros75(r75List);
		// sintegra.setRegistros51(r51List);
		// sintegra.setRegistros53(r53List);
		// sintegra.setRegistros60M(r60mList);

		sintegra.gerarRegistros90();

		System.out.println("*** Teste Sintegra ***");

		try {

			Writer fw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("SintegraTest.txt"), "ISO-8859-1"));
			SintegraWriter sintegraWriter = new SintegraWriter(fw);

			sintegraWriter.write(sintegra);

			sintegraWriter.flush();
			sintegraWriter.close();
		} catch (IOException ex) {
			Logger.getLogger(SintegraTest.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}