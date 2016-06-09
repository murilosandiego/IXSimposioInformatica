package br.edu.ifnmg.ixsimposioinformatica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.adapter.ProgramacaoAdapter;
import br.edu.ifnmg.ixsimposioinformatica.domain.Programacao;
import br.edu.ifnmg.ixsimposioinformatica.fragment.ProgramacaoFragment;
import fr.ganfra.materialspinner.MaterialSpinner;

public class ProgramacaoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ArrayList<Programacao> listaProgramacaoTerca;
    private ArrayList<Programacao> listaProgramacaoQuarta;
    private ArrayList<Programacao> listaProgramacaoQuinta;
    private ArrayList<Programacao> listaProgramacaoSexta;
    private ArrayList<Programacao> listaCompleta;
    ArrayList<Programacao> programacoes;
    ArrayList<String> datasFormatas;
    private ListView listView;
    private ArrayList<Date> datas;
    private ArrayList<String> strDatas;
    private SimpleDateFormat formatador
            = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacao);


        if (savedInstanceState != null) {
            listaProgramacaoTerca = savedInstanceState.getParcelableArrayList("listaProgramacaoTerca");
            listaProgramacaoQuarta = savedInstanceState.getParcelableArrayList("listaProgramacaoQuarta");
            listaProgramacaoQuinta = savedInstanceState.getParcelableArrayList("listaProgramacaoQuinta");
            listaProgramacaoSexta = savedInstanceState.getParcelableArrayList("listaProgramacaoSexta");
            listaCompleta = savedInstanceState.getParcelableArrayList("listaCompleta");
        } else {
            datas = new ArrayList<>();
            Date data = null;
            try {
                data = formatador.parse("14/06/2016");
                datas.add(data);
                data = formatador.parse("15/06/2016");
                datas.add(data);
                data = formatador.parse("16/06/2016");
                datas.add(data);
                data = formatador.parse("17/06/2016");
                datas.add(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            strDatas = new ArrayList<>();
            strDatas.add("14/06 - Terça-Feira");
            strDatas.add("15/06 - Quarta-Feira");
            strDatas.add("16/06 - Quinta-Feira");
            strDatas.add("17/06 - Sexta-Feira");

            ProgramacaoFragment pf = new ProgramacaoFragment();

            this.listaCompleta = new ArrayList<>();
            this.listaProgramacaoTerca = new ArrayList<>();
            inserirProgramacaoTerca();
            this.listaProgramacaoQuarta = new ArrayList<>();
            inserirProgramacaoQuarta();
            this.listaProgramacaoQuinta = new ArrayList<>();
            inserirProgramacaoQuinta();
            this.listaProgramacaoSexta = new ArrayList<>();
            inserirProgramacaoSexta();

            for (Programacao itemProgramacao : listaProgramacaoTerca) {
                listaCompleta.add(itemProgramacao);
            }

            for (Programacao itemProgramacao : listaProgramacaoQuarta) {
                listaCompleta.add(itemProgramacao);
            }

            for (Programacao itemProgramacao : listaProgramacaoQuinta) {
                listaCompleta.add(itemProgramacao);
            }

            for (Programacao itemProgramacao : listaProgramacaoSexta) {
                listaCompleta.add(itemProgramacao);
            }

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        Date dataAtual = getData();


        listView = (ListView) findViewById(R.id.ltvProgramacao);


        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spiData);


        programacoes = new ArrayList<>();

        if (dataAtual.equals(datas.get(0))) {
            spinner.setSelection(1);
        } else if (dataAtual.equals(datas.get(1))) {
            spinner.setSelection(2);
        } else if (dataAtual.equals(datas.get(2))) {
            spinner.setSelection(3);
        } else if (dataAtual.equals(datas.get(3))) {
            spinner.setSelection(4);
        }

        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        //  ArrayAdapter<Date> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,datas);

        datasFormatas = new ArrayList<>();

        datasFormatas.add("Programação Completa");
        datasFormatas.add(formatador.format(datas.get(0)) + " - Terça-Feira");
        datasFormatas.add(formatador.format(datas.get(1)) + " - Quarta-Feira");
        datasFormatas.add(formatador.format(datas.get(2)) + " - Quinta-Feira ");
        datasFormatas.add(formatador.format(datas.get(3)) + " - Sexta-Feira ");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datasFormatas);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private Date getData() {
        Date aDate = new Date();
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(aDate);
        myCalendar.set(Calendar.HOUR_OF_DAY, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        myCalendar.set(Calendar.MILLISECOND, 0);
        return myCalendar.getTime();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            listView.setAdapter(new ProgramacaoAdapter(getBaseContext(), listaCompleta));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ProgramacaoDetalhadaActivity.class);
                    intent.putExtra("programacao", listaCompleta.get(position));
                    startActivity(intent);
                }
            });
        } else if (position == 1) {
            listView.setAdapter(new ProgramacaoAdapter(getBaseContext(), listaProgramacaoTerca));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ProgramacaoDetalhadaActivity.class);
                    intent.putExtra("programacao", listaProgramacaoTerca.get(position));
                    startActivity(intent);
                }
            });
        } else if (position == 2) {
            listView.setAdapter(new ProgramacaoAdapter(getBaseContext(), listaProgramacaoQuarta));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ProgramacaoDetalhadaActivity.class);
                    intent.putExtra("programacao", listaProgramacaoQuarta.get(position));
                    startActivity(intent);
                }
            });
        } else if (position == 3) {
            listView.setAdapter(new ProgramacaoAdapter(getBaseContext(), listaProgramacaoQuinta));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ProgramacaoDetalhadaActivity.class);
                    intent.putExtra("programacao", listaProgramacaoQuinta.get(position));
                    startActivity(intent);
                }
            });
        } else if (position == 4) {
            listView.setAdapter(new ProgramacaoAdapter(getBaseContext(), listaProgramacaoSexta));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ProgramacaoDetalhadaActivity.class);
                    intent.putExtra("programacao", listaProgramacaoSexta.get(position));
                    startActivity(intent);
                }
            });
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listaProgramacaoTerca", (ArrayList<Programacao>) listaProgramacaoTerca);
        outState.putParcelableArrayList("listaProgramacaoQuarta", (ArrayList<Programacao>) listaProgramacaoQuarta);
        outState.putParcelableArrayList("listaProgramacaoQuinta", (ArrayList<Programacao>) listaProgramacaoQuinta);
        outState.putParcelableArrayList("listaProgramacaoSexta", (ArrayList<Programacao>) listaProgramacaoSexta);
        outState.putParcelableArrayList("listaCompleta", (ArrayList<Programacao>) listaCompleta);

    }

    private void inserirProgramacaoTerca() {
        String horario = "Terça-Feira - Horário: 13:30h às 18:00h";
        String conteudo = "Credenciamento";
        String palestrante = null;
        String detalhe;
        String urlLattes;
        String urlLattes2;

        Programacao programacao = new Programacao();
        programacao.setConteudo(conteudo);
        programacao.setHorario(horario);

        listaProgramacaoTerca.add(programacao);


        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 13:30h às 17:05h - Minicurso\n(Laboratório 03)\n";
        conteudo = "Introdução a Análise de Redes Sociais -ARS";
        palestrante = "Instrutor: Aline Ferreira da Conceição";
        detalhe = "O objetivo principal na oferta deste minicurso é propiciar os subsídios necessários para o entendimento da Análise de Redes Sociais- ARS mediante a compreensão de seus principais conceitos, técnicas e aplicações.\n\nDuraçao: 4 horas\n\nVagas: 30 pessoas";
        urlLattes = "http://lattes.cnpq.br/7603440915971604";

        programacao.setFavorito(true);

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 15:25h às 17:05h - Minicurso\n(Laboratório 04)\n";
        conteudo = "Automação Residencial utilizando Arduino";
        palestrante = "Instrutor: Diego Alves da Costa ";
        detalhe = "Serão apresentados as tecnologias utilizadas para a construção do hardware e comunicação com a linguagem java via serial, para armazenamento dos dados coletados por sensores. A prática será a comunicação entre java, MYSQL e Arduino(Linguagem C).\n\nDuraçao: 2 horas\n\nVagas: 30 pessoas\n\nPré-requisitos: Programação Estruturada e Orientada a Objeto.";
        urlLattes = "http://lattes.cnpq.br/7603440915971604";

        programacao.setFavorito(true);

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 13:30h às 15:10h - Minicurso\n(Laboratório 01)\n";
        conteudo = "Introdução ao Desenvolvimento de Games e Aplicações 3D, utilizando a Unreal Engine 4";
        palestrante = "Instrutor: Thállys Lisboa Simões ";
        detalhe = "O Minicurso tem como proposta introduzir as informações necessárias para que um usuário possa iniciar no mercado de desenvolvimento de games e aplicações em 3D. Quais são os requisitos e como se trabalhar utilizando a ferramenta de desenvolvimento Unreal Engine 4. Abordar as melhores técnicas de desenvolvimento e mostrar as principais funcionalidades da ferramenta, introduzindo assim conceitos básicos de uma das principais ferramentas do mercado da área citada, essencial para início de estudos por parte dos alunos interessados no desenvolvimento.\n\nDuração: 2 horas\n\nVagas: 30 pessoas";
        urlLattes = "http://lattes.cnpq.br/6132644554688255";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 15:25h às 17:05h - Minicurso\n(Laboratório 02)\n";
        conteudo = "Edição de imagens no Fireworks com foco para web";
        palestrante = "Instrutor: João Victor Melo Lima e João Victor Rodrigues de Souza";
        detalhe = "Conceitos e uso de ferramentas contidas no software e diferentes técnicas para edição de imagens.\n\nDuração: 2 horas\n\nVagas: 30";
        urlLattes = "http://lattes.cnpq.br/0282255641844854";
        urlLattes2 = "http://lattes.cnpq.br/3621073644662840";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 13:30h às 17:05h - Minicurso\n(Laboratório de Matemática)\n";
        conteudo = "Gestão Sustentável de Projetos com PM Canvas";
        palestrante = "Instrutores: Valnides Araujo Costa  e  Odorico Guilherme Veloso da Silva";
        detalhe = "O participante terá acesso à metodologia de Gestão de Projetos “Project Model Canvas”  que integra conceitos da neurociência à concepção, integração, resolução e comunicação de um projeto de forma prática e ágil. No minicurso o participante irá desenvolver um plano de projeto usando essa metodologia, enquanto abstrai os conceitos que a fundamentam.\n\nDuração: 4 horas\n\nVagas: 30";
        urlLattes = "http://lattes.cnpq.br/1702362793353915";
        urlLattes2 = "http://lattes.cnpq.br/1927851787428878";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 13:30h";
        conteudo = "Jogos";
        palestrante = null;

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoTerca.add(programacao);

        programacao = new Programacao();

        horario = "Terça-Feira - Horário: 19:00h às 22:30h";
        conteudo = "CineTech";
        palestrante = null;

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoTerca.add(programacao);

        for (Programacao itemProgramacao : listaProgramacaoTerca) {
            listaCompleta.add(itemProgramacao);
        }

    }

    private void inserirProgramacaoQuarta() {

        String horario;
        String conteudo;
        String palestrante;
        String detalhe;
        String urlLattes;
        String urlLattes2;

        Programacao

                programacao = new Programacao();

        conteudo = "Criação de um site profissional em 4 horas utilizando o Gerenciador de Conteúdo “Joomla”";
        palestrante = "Instrutor: Rômulo Silveira Ramos ";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 01)\n";
        detalhe = "Neste minicurso o aluno aprenderá na prática a desenvolver um site profissional (Com Menus atrativos, imagens, vídeos, links, calendários, login, etc) com acesso à banco de dados utilizando o gerenciador de conteúdo Joomla, que é um dos CMS mais utilizados no mundo.\n\nDuração: 4 horas\n\nVagas: 30 pessoas";
        urlLattes = "http://lattes.cnpq.br/8767023285622273";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Webjornalismo e desenvolvimento sustentável";
        palestrante = "Instrutor: Andreia Pereira da Silva";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Auditório da Biblioteca)\n";
        detalhe = "A sociedade atual vivencia paradoxalmente o avanço da tecnologia e o retrocesso no que se refere à busca pelo desenvolvimento sustentável. Apesar dos avanços em prol do meio ambiente, torna-se inadmissível perante toda a competência tecnológica mundial que o ser humano ainda não reconheça plenamente a importância da sustentabilidade, colocando-a em prática. Dessa forma, o objetivo do minicurso intitulado “Webjornalismo e desenvolvimento sustentável” consiste em discutir os rumos do jornalismo ambiental exercido na internet em defesa da sustentabilidade, tendo como centro dessa discussão a sociedade. Também há o propósito de reconhecer as ferramentas da internet para abordar a pauta ambiental da forma mais completa e dinâmica possível. Objetiva-se ainda debater sobre as potencialidades das mídias sociais na denúncia das problemáticas ambientais e ao mesmo tempo na promoção de campanhas e discussões de interesse social.\n\nDuração: 4 horas\n\nVagas: 30 pessoas";
        urlLattes = "http://lattes.cnpq.br/5211915868199706";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Constructor 2: Uma engine para desenvolver jogos multiplataformas";
        palestrante = "Instrutor: Wander Kennedy Batista Cordeiro";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 02)\n";
        detalhe = "Será apresentado o potencial da engine Costructor 2 para desenvolver jogos para diversas plataformas, tais como: HTML 5, Android, IOS, Windows Phone, Chrome Web e outras. Será exposto os conceitos básicos para o desenvolvimento de um jogo e uma exemplificação prática.\n\nDuração: 4 horas\n\nVagas: 15";
        urlLattes = "http://lattes.cnpq.br/0985831412778599";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Introdução ao Desenvolvimento de Aplicativos Android";
        palestrante = "Instrutor: Murilo Sandiego e Wellington Brito";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 03)\n";
        detalhe = "Conceitos básicos em programação para dispositivos Android. Introdução às práticas necessárias para se criar um aplicativo “Hello world”, configuração de layouts de tela dentre outras funcionalidades básicas que permitirá iniciar o desenvolvimento de um aplicativo Android. Por exemplo, uma simples calculadora, Lista de contatos e etc.\n\nDuração: 4 horas\n\nVagas: 15 pessoas\n\nPré-requisitos: Conhecimento em programação orientada a objetos. Cabo USB Smartphones. Linguagem Java. Básico sobre Xml.";
        urlLattes = "http://lattes.cnpq.br/6998057778263197";
        urlLattes2 = "http://lattes.cnpq.br/5124289466117550";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Desenvolvendo seu próprio Arduino";
        palestrante = "Instrutor: João Victor de Oliveira Novaes";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 04)\n";
        detalhe = "Instruir os participantes a desenvolver circuitos eletrônicos,bem como desenvolver sua própria placa micro controlada baseada na plataforma Arduíno.\n\nPré-requisitos: Conhecer um pouco da plataforma Arduino.";
        urlLattes = "http://lattes.cnpq.br/6394518466477826";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Método lógico para redação científica (Primeira parte)";
        palestrante = "Instrutor: Valnides Araujo Costa";
        horario = "Quarta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório de Matemática)\n";
        detalhe = "Os cursistas participarão de exercícios práticos de escrita científica: organização da Revisão de Literatura, Formulação do Problema de Pesquisa, Elaboração e apresentação textual dos delineamentos de pesquisa, apresentação e análise dos resultados de pesquisa e escrita do resumo de artigos, utilizando-se da matriz do método lógico para redação científica e de técnicas de “engenharia reversa”.\n\nDuração: 8 horas\n\nVagas: 30\n\nPré-requisitos: Conhecer um pouco da plataforma Arduino.";
        urlLattes = "http://lattes.cnpq.br/1702362793353915";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Abertura Oficial do evento";
        palestrante = null;
        horario = "Quarta-Feira - Horário: 13:30h";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Palestra 1: Aplicações Tecnológicas Sustentáveis";
        palestrante = "Palestrantes: Frederico Batista e Thalis Antunes de Souza";
        horario = "Quarta-Feira - Horário: 13:30h - Palestra - Anfiteatro";
        detalhe = "No mundo atual as áreas do conhecimento vem convergindo trazendo cada vez mais a necessidade do desenvolvimento de projetos multidisciplinares. Nesta palestra iremos abordar desde reciclagem de lixo eletrônicos, passando pela importância do trabalho em comunidade e encerrando com o uso de geotecnologias e Veiculos Autônomos Não Tripulados – VANTs em aplicações de planejamento e monitoramento em empreendimentos agrícolas, sempre minimizando os impactos ambientais nos mesmos.";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Mostra de Trabalhos Científicos";
        palestrante = "";
        detalhe = "Artigos científicos abordando revisões do estado da arte e novas perspectivas de investigação, ideias e/ou arquiteturas inovadoras, soluções e/ou aplicações para problemas reais, trabalhos empíricos e/ou de avaliação, estudos de caso, trabalhos de conclusão de curso, entre outros, que se enquadrem nas temáticas do simpósio.";
        horario = "Quarta-Feira - Horário: 15:30h às 18:00h - Hall Ensino Superior";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Feira de Projetos";
        palestrante = "";
        detalhe = "A Feira de Projetos, evento presente no IX Simpósio de Informática do IFNMG – Campus Januária, tem o intuito de promover a integração dos cursos da área da Tecnologia da Informação presentes no IFNMG e demais Instituições de Ensino participantes. É um momento único, dedicado para que todos possam conhecer os diversos trabalhos desenvolvidos pela área.";
        horario = "Quarta-Feira - Horário: 15:30h às 18:00h - Hall Ensino Superior";

        programacao.setDetalhe(detalhe);
        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Apresentação Oral de Artigos";
        palestrante = "";
        horario = "Quarta-Feira - Horário: 16:00h às 18:00h";
        detalhe = " Apresentação oral de artigos científicos abordando revisões do estado da arte e novas perspectivas de investigação, ideias e/ou arquiteturas inovadoras, soluções e/ou aplicações para problemas reais, trabalhos empíricos e/ou de avaliação, estudos de caso, trabalhos de conclusão de curso, entre outros, que se enquadrem nas temáticas do simpósio.";

        programacao.setDetalhe(detalhe);
        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "CineTech";
        palestrante = null;
        horario = "Quarta-Feira - Horário: 19:00h às 22:30h";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "SketchUp Básico – Maquetes 3D";
        palestrante = "Instrutor: Karla Ulisses Lima e Débora Coutinho Santos Mesquita";
        horario = "Quarta-Feira - Horário: 19:00h às 22:30h - Minicurso\n(Laboratório 01)\n";
        detalhe = "Elaboração de maquetes eletrônicas 3D.\n\nDuração: 4 horas\n\nVagas: 15 pessoas\n\nPré-requisitos: Conhecimento básico de informatica.";
        urlLattes = "http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4464767Z1";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Arduino: uma prática em automação de portas";
        palestrante = "Instrutor: Arley Oliveira da Mota";
        horario = "Quarta-Feira - Horário: 19:00h às 22:30h - Minicurso\n(Laboratório 04)\n";
        detalhe = "Pretende-se construir um protótipo de uma ferramenta que gerencie portas, utilizando tecnologias open source.\n\nDuração: 4 horas\n\nVagas: 15 pessoas\n\nPré-requisitos: Conhecimento básico em linguagem de programação C/C++ e PHP; Noções de redes de computadores; Noções básicas de eletrônica.";
        urlLattes = "http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4464767Z1";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Desenvolvimento de Apps para Celular utilizando plataforma MIT App Inventor";
        palestrante = "Instrutor: Marylia Marinho dos Santos e Matheus de Souza Teles";
        horario = "Quarta-Feira - Horário: 19:00h às 22:30h - Minicurso\n(Laboratório 03)\n";
        detalhe = "Introdução à plataforma. Montando o Layout do aplicativo. Programação em blocos. Testando o aplicativo. Compilando o .apk. Exemplo prático: criar um aplicativo de bloco de notas para Android.\n\nDuração: 4 horas\n\nVagas: 30 pessoas\n\nPré-requisitos: Recomenda-se conhecimento básico em internet.";
        urlLattes = "http://lattes.cnpq.br/7111901626727910";
        urlLattes2 = "http://lattes.cnpq.br/4093432325764424";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoQuarta.add(programacao);

        programacao = new Programacao();

        conteudo = "Site Responsivo: Entendo o Bootstrap";
        palestrante = "Instrutor: André Fellype Matos Mota e Vínicius Dias de Souza ";
        horario = "Quarta-Feira - Horário: 19:00h às 22:30h - Minicurso\n(Laboratório 02)\n";
        detalhe = "Css para Bootstrap. Componentes do Bootstrap. JavaScript do Bootstrap\n\nDuração: 4 horas\n\nVagas: 30 pessoas\n\nPré-requisitos: Conhecimento básico de HTML e CSS";
        urlLattes = "http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4043533T5";
        urlLattes2 = "http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K8149071H6";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoQuarta.add(programacao);


        for (Programacao itemProgramacao : listaProgramacaoQuarta) {
            listaCompleta.add(itemProgramacao);
        }

    }

    private void inserirProgramacaoQuinta() {
        String horario;
        String conteudo;
        String palestrante;
        String detalhe;
        String urlLattes;
        String urlLattes2;

        Programacao programacao = new Programacao();

        conteudo = "Introdução ao jQuery Mobile";
        palestrante = "Instrutor: Gustavo Linhares Lélis Frota";
        horario = "Quinta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 02)\n";
        detalhe = "O aluno irá aprender a utilizar os principais recurso do framework jQuery mobile. Criar uma um aplicativo mobile.\n\nDuração: 4 horas\n\nVagas: 15 pessoas\n\nPré-requisito: Html, CSS, JavaScript e jQuery (Sendo os dois últimos conhecimento básico)";
        urlLattes = "http://lattes.cnpq.br/1707513800345644";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "ORMLite no Android";
        palestrante = "Instrutor: André Fellype Matos Mota e Hélder Seixas Lima";
        horario = "Quinta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 03)\n";
        detalhe = "Como utilizar a lib ORMLite no Android para facilitar o mapeamento de nossos objetos na API de persistência do Android, no caso o SQLite. O ORMLite é uma entidade ORM (Object Relational Mapping) Java, para utilizá-lo no Android precisamos importar apenas dois arquivos simples .jar e então começar com as extensões e as respectivas annotations, alias igualmente em JPA.\n\nDuração: 4 horas\n\nVagas: 30 pessoas\n\nPré-requisitos: Liguagem JAVA";
        urlLattes = "http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4043533T5";
        urlLattes2 = "http://lattes.cnpq.br/1485670537892856";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);
        programacao.setUrlLattes2(urlLattes2);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Internet das Coisas Para Automação Residencial";
        palestrante = "Instrutor: Josué Batista Antunes";
        horario = "Quinta-Feira - Horário: 09:25h às 11:05h - Minicurso\n(Laboratório 04)\n";
        detalhe = "Serão apresentados os conceitos básicos sobre Internet das Coisas (IoT) e Automação Residencial. Também serão apresentadas algumas plataformas desenvolvidas para fornecer serviços como segurança que utilizam os dispositivos da IoT. Durante o momento prático serão apresentados os dispositivos “Philips Hue” e “Controlador Switch Wemo Belkin” e mostrar como estes dispositivos podem ser utilizados na automação residencial.\n\nDuração: 2 horas\n\nVagas: 30\n\nPré-requisitos: Conhecimentos básicos em redes de computadores e sistemas operacionais";
        urlLattes = "http://lattes.cnpq.br/4143116376815444";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Método lógico para redação científica (segunda parte)";
        palestrante = "Instrutor: Valnides Araujo Costa";
        horario = "Quinta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório de Matemática)\n";
        detalhe = "Os cursistas participarão de exercícios práticos de escrita científica: organização da Revisão de Literatura, Formulação do Problema de Pesquisa, Elaboração e apresentação textual dos delineamentos de pesquisa, apresentação e análise dos resultados de pesquisa e escrita do resumo de artigos, utilizando-se da matriz do método lógico para redação científica e de técnicas de “engenharia reversa”.\n\nDuração: 8 horas\n\nVagas: 30\n\nPré-requisitos: Ter cursado a disciplina de Métodos de Pesquisa ou similar.";
        urlLattes = "http://lattes.cnpq.br/1702362793353915";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Guia Prático de Ambientes em Virtualização";
        palestrante = "Instrutor: Carlos Manuel Pereira da Costa Filho";
        horario = "Quinta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Laboratório 01)\n";
        detalhe = "Conceitos de Virtualização, Tecnologias disponíveis, Aulas práticas em ambientes virtuais, criação e administração de máquinas virtuais, comunicação gestão em rede.\n\nDuração: 4 horas\n\nVagas: 30\n\nPré-requisitos: Conhecimentos básicos de informática. Conhecimentos de rede.";
        urlLattes = "http://lattes.cnpq.br/0345945846702957";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Edição de vídeos para Youtube com Sony Vegas";
        palestrante = "Instrutor: Welington dos Santos Silva";
        horario = "Quinta-Feira - Horário: 07:30h às 11:05h - Minicurso\n(Anfiteatro)\n";
        detalhe = "Apresentar o ambiente de edição do Sony Vegas, edição de vídeo para o Youtube. Será mostrado passo a passo desde a gravação do vídeo até a finalização para postagem.\n\nDuração: 4 horas\n\nVagas: 70 pessoas";
        urlLattes = "http://lattes.cnpq.br/6175548103586373";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Como fazer Mapas Mentais? Turma 1";
        palestrante = "Instrutor: Geiza Mendonça";
        horario = "Quinta-Feira - Horário: 07:30h às 09:10h - Minicurso\n(Laboratório Móvel – Carreta)\n";
        detalhe = "Verificar a possibilidade do uso do mapa mental como facilitador para a criação de conhecimento. A oficina vai ser aplicada juntamente com um tema para as pessoas que vão participar onde vamos avaliar imagens e atitudes de cada um e as informações em gerais\n\nDuração: 2 horas\n\nVagas: 15 pessoas\n\nPré-requisitos: Ter concluído o ensino médio completo.";
        urlLattes = "http://lattes.cnpq.br/6175548103586373";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();
//
        conteudo = "Como fazer Mapas Mentais? Turma 2";
        palestrante = "Instrutor: Geiza Mendonça";
        horario = "Quinta-Feira - Horário: 09:25h às 11:05h - Minicurso\n(Laboratório Móvel – Carreta)\n";
        detalhe = "Verificar a possibilidade do uso do mapa mental como facilitador para a criação de conhecimento. A oficina vai ser aplicada juntamente com um tema para as pessoas que vão participar onde vamos avaliar imagens e atitudes de cada um e as informações em gerais\n\nDuração: 2 horas\n\nVagas: 15 pessoas\n\nPré-requisitos: Ter concluído o ensino médio completo.";
        urlLattes = "http://lattes.cnpq.br/6175548103586373";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);
        programacao.setUrlLattes(urlLattes);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        horario = "Quinta-Feira - Horário: 07:30h";
        conteudo = "Jogos";
        palestrante = null;

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Palestra 2:  Start suas ideias! Up sua região!";
        palestrante = "Palestrante: Cecícia Bettero";
        horario = "Quinta-Feira - A partir das 13:30h – Palestra - Anfiteatro";
        detalhe = "Abordar o comportamento da criatividade aliado ao empreendedorismo, que é propulsor do desenvolvimento econômico e como o modelo de Startups pode transformar sua ideia em solução.";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Palestra 3:  Programa de apoio às tecnologias aplicadas ao desenvolvimento sustentável.";
        palestrante = "Palestrante: Haroldo de Moraes Lopes";
        horario = "Quinta-Feira - A partir das 13:30h – Palestra - Anfiteatro";
        detalhe = "Conhecimento científicos no Norte de Minas Gerais. Apoio e incentivo às instituições de ensino e os pesquisadores. Empreendedorismo no Norte de Minas Gerais. Histórico da Fundetec. Incubadora de Base Tecnológica da Fundetec e o processo de incubação. Projetos apoiados pela Fundetec.";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Mostra de Trabalhos Científicos";
        palestrante = "";
        detalhe = "Artigos científicos abordando revisões do estado da arte e novas perspectivas de investigação, ideias e/ou arquiteturas inovadoras, soluções e/ou aplicações para problemas reais, trabalhos empíricos e/ou de avaliação, estudos de caso, trabalhos de conclusão de curso, entre outros, que se enquadrem nas temáticas do simpósio.";
        horario = "Quinta-Feira - Horário: 15:30h às 18:00h - Hall Ensino Superior";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Feira de Projetos";
        palestrante = "";
        detalhe = "A Feira de Projetos, evento presente no IX Simpósio de Informática do IFNMG – Campus Januária, tem o intuito de promover a integração dos cursos da área da Tecnologia da Informação presentes no IFNMG e demais Instituições de Ensino participantes. É um momento único, dedicado para que todos possam conhecer os diversos trabalhos desenvolvidos pela área.";
        horario = "Quinta-Feira - Horário: 15:30h às 18:00h - Hall Ensino Superior";


        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Apresentação Oral de Artigos";
        palestrante = "";
        horario = "Quinta-Feira - Horário: 16:00h às 18:00h";
        detalhe = " Apresentação oral de artigos científicos abordando revisões do estado da arte e novas perspectivas de investigação, ideias e/ou arquiteturas inovadoras, soluções e/ou aplicações para problemas reais, trabalhos empíricos e/ou de avaliação, estudos de caso, trabalhos de conclusão de curso, entre outros, que se enquadrem nas temáticas do simpósio.";

        programacao.setDetalhe(detalhe);
        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "BOMBERTADS";
        palestrante = "";
        horario = "Quinta-Feira - Horário: 17:00h e 18:40h";
        detalhe = "BOMBER TADS é um game de estratégia baseado no clássico ?Bomber Man?. Foi desenvolvido em 2010 para o IV Simpósio de Informática pelos egressos do curso de Tecnologia em Análise e Desenvolvimento de Sistemas (TADS) do IFNMG ? Câmpus Januária: Herberth Willian, Carlos Manuel e Alex Silva.";

        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);
        programacao.setDetalhe(detalhe);

        listaProgramacaoQuinta.add(programacao);

        programacao = new Programacao();

        conteudo = "Encontro de Ex-Alunos \nEncontro de professores\nConfraternização";
        palestrante = null;
        horario = "Quinta-Feira - Horário: 19:00h às 22:30h";


        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setHorario(horario);


        listaProgramacaoQuinta.add(programacao);

        for (Programacao itemProgramacao : listaProgramacaoQuinta) {
            listaCompleta.add(itemProgramacao);
        }

    }

    private void inserirProgramacaoSexta() {
        String horario;
        String conteudo;
        String palestrante;
        String detalhe;

        Programacao programacao = new Programacao();

        horario = "Sexta-Feira - Horário: 07:30h às 11:05h";
        conteudo = "Palestra 3";
        palestrante = "Palestrante 3";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoSexta.add(programacao);


        programacao = new Programacao();

        horario = "Sexta-Feira - Horário: 14:00h às 18:00h";
        conteudo = "Maratona de Programação";
        palestrante = "";
        detalhe = "A Maratona de Programação é uma competição entre times compostos por três alunos (sob a coordenação de um professor), que irão resolver durante 4 horas o maior número possível de problemas através da composição de algoritmos, utilizando as linguagens de programação: C, Java ou Pascal.Os problemas somente serão conhecidos no início da competição. As equipes terão à sua disposição apenas um computador, podendo também consultar materiais impressos (livros, listagens e manuais) para vencer a batalha contra o relógio. Lembrando que, os materiais supracitados não serão fornecidos pela Comissão Organizadora.";

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoSexta.add(programacao);

        programacao = new Programacao();

        horario = "Sexta-Feira - Horário: 13:30h";
        conteudo = "QuizTech";
        palestrante = "";
        detalhe = String.valueOf(R.string.quiztech);

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);
        programacao.setDetalhe(detalhe);

        listaProgramacaoSexta.add(programacao);

        programacao = new Programacao();

        horario = "Sexta-Feira - Horário: 18:00h";
        conteudo = "Encerramento do Evento";
        palestrante = null;

        programacao.setHorario(horario);
        programacao.setConteudo(conteudo);
        programacao.setPalestrante(palestrante);

        listaProgramacaoSexta.add(programacao);

        for (Programacao itemProgramacao : listaProgramacaoSexta) {
            listaCompleta.add(itemProgramacao);
        }
    }
}
