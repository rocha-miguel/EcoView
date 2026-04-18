<h1 align="center">
  🌱 EcoView
</h1>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/rocha-miguel/EcoView?color=%2304D361">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/rocha-miguel/EcoView">
  <a href="https://github.com/rocha-miguel/EcoView/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/rocha-miguel/EcoView">
  </a>
</p>

<p align="center">
  <strong>Monitore sua pegada de carbono e acompanhe as condições climáticas da sua região.</strong>
</p>

---

## 📖 Sobre o Projeto

O **EcoView** é um aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose**. O principal objetivo do app é conscientizar os usuários sobre o seu impacto ambiental por meio de uma **Calculadora de Emissão de CO₂**. Além disso, o aplicativo também fornece informações climáticas da localidade do usuário, incentivando uma rotina mais sustentável e alinhada ao meio ambiente.

## ✨ Funcionalidades

- 🔐 **Autenticação de Usuários:** cadastro, login e atualização de perfil com armazenamento local de dados.
- 🧮 **Calculadora de CO₂:** cálculo personalizado da pegada de carbono com base em:
  - 🚗 Distância percorrida em transportes
  - 🥩 Hábitos alimentares
  - ⚡ Consumo de energia elétrica
- 📊 **Histórico de Emissões:** acompanhamento dos cálculos anteriores para visualizar a evolução ao longo do tempo.
- 🌤️ **Clima da Região:** integração com a API do [Open-Meteo](https://open-meteo.com/) para obter dados climáticos atualizados, como temperatura atual, máximas e mínimas do dia e condições do tempo.

## 🚀 Tecnologias e Arquitetura

O aplicativo foi construído com ferramentas modernas do ecossistema Android:

- **[Kotlin](https://kotlinlang.org/):** linguagem principal do projeto.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** construção de interfaces nativas de forma declarativa.
- **[Room Database](https://developer.android.com/training/data-storage/room):** persistência de dados locais.
- **[Retrofit 2](https://square.github.io/retrofit/)** e **[Gson](https://github.com/google/gson):** consumo de API e serialização de JSON.
- **[Coil](https://coil-kt.github.io/coil/compose/):** carregamento de imagens no Compose.
- **[Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation):** navegação entre telas.

## 📂 Estrutura do Projeto

A estrutura do projeto foi organizada para separar as responsabilidades de forma clara:

- `components/`: componentes de UI reutilizáveis.
- `dao/` e `repository/`: camada de acesso a dados locais com Room.
- `model/`: modelos de dados e respostas da API.
- `screens/`: telas do aplicativo construídas com Jetpack Compose.
- `service/`: regras de negócio e serviços, como cálculo de CO₂ e integração com API de clima.

## 💻 Como Executar o Projeto

### Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- [Android Studio](https://developer.android.com/studio)
- [Git](https://git-scm.com/)

### Passo a Passo

1. Faça o clone do repositório:
   ```bash
   git clone https://github.com/rocha-miguel/EcoView.git
   ```

2. Abra o **Android Studio**.

3. Clique em **Open** e selecione a pasta do projeto `EcoView` que você acabou de clonar.

4. Aguarde o **Gradle** baixar as dependências e sincronizar o projeto.

5. Conecte o seu dispositivo Android via cabo USB (com a Depuração USB ativada) ou inicie um **Emulador (AVD)** no Android Studio.

6. Clique no botão de **Run** (ícone de play verde ▶️) ou pressione `Shift + F10` para rodar o aplicativo no dispositivo selecionado.

---
Feito com 💚 para um futuro mais sustentável.
