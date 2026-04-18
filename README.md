<h1 align="center">
  🌱 EcoView
</h1>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/seu-usuario/EcoView?color=%2304D361">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/seu-usuario/EcoView">
  <a href="https://github.com/seu-usuario/EcoView/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/seu-usuario/EcoView">
  </a>
</p>

<p align="center">
  <strong>Monitore sua pegada de carbono e acompanhe as condições climáticas da sua região.</strong>
</p>

<hr>

## 📖 Sobre o Projeto

O **EcoView** é um aplicativo Android desenvolvido inteiramente em **Kotlin** e **Jetpack Compose**. O principal objetivo do app é conscientizar os usuários sobre o seu impacto ambiental através de uma **Calculadora de Emissão de CO₂**. Além disso, o aplicativo fornece informações climáticas em tempo real para a sua localidade, promovendo uma rotina mais sustentável e alinhada ao meio ambiente.

## ✨ Funcionalidades

- 🔐 **Autenticação de Usuários:** Sistema de cadastro, login e atualização de perfil com armazenamento local de dados.
- 🧮 **Calculadora de CO₂:** Cálculo personalizado da sua pegada de carbono baseado em:
  - 🚗 Distância percorrida em transportes.
  - 🥩 Hábitos alimentares (vegana, vegetariana, pouca carne, muita carne).
  - ⚡ Consumo de energia elétrica.
- 📊 **Histórico de Emissões:** Acompanhe os resultados dos seus cálculos anteriores e observe sua evolução ao longo do tempo.
- 🌤️ **Previsão do Tempo (Clima):** Integração com a API do [Open-Meteo](https://open-meteo.com/) para obter dados climáticos atualizados, como temperatura atual, sensação térmica, cobertura de nuvens e previsões máximas/mínimas do dia.

## 🚀 Tecnologias e Arquitetura

O aplicativo foi construído com as mais modernas ferramentas do ecossistema Android:

- **[Kotlin](https://kotlinlang.org/):** Linguagem de programação principal.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Kit de ferramentas moderno para construção de interfaces nativas de forma declarativa.
- **[Room Database](https://developer.android.com/training/data-storage/room):** Abstração sobre o SQLite para persistência de dados locais (Usuários e Histórico).
- **[Retrofit 2](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson):** Para requisições de rede (consumo da API de clima) e serialização/deserialização de JSON.
- **[Coil](https://coil-kt.github.io/coil/compose/):** Carregamento e cache de imagens otimizado para Compose.
- **[Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation):** Para gerenciamento do fluxo e navegação entre telas.

## 📂 Estrutura do Projeto

A arquitetura do projeto está dividida para separar responsabilidades de forma clara:

- `components/`: Componentes de UI reutilizáveis em todo o app.
- `dao/` & `repository/`: Camada de acesso aos dados locais utilizando Room.
- `model/`: Classes de modelo (data classes) para Banco de Dados e Respostas da API.
- `screens/`: Telas do aplicativo construídas com Jetpack Compose (Login, Home, Histórico, etc).
- `service/`: Serviços de lógica de negócio (como a `CalculadoraCO2`) e chamadas de API (`WeatherApiService`).

## 💻 Como Executar o Projeto

### Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina o [Android Studio](https://developer.android.com/studio) (versão mais recente recomendada) e o [Git](https://git-scm.com/).

### Passo a Passo

1. Faça um clone deste repositório:
   ```bash
   git clone https://github.com/seu-usuario/EcoView.git
   ```

2. Abra o **Android Studio**.

3. Clique em **Open** e selecione a pasta do projeto `EcoView` que você acabou de clonar.

4. Aguarde o **Gradle** baixar as dependências e sincronizar o projeto.

5. Conecte o seu dispositivo Android via cabo USB (com a Depuração USB ativada) ou inicie um **Emulador (AVD)** no Android Studio.

6. Clique no botão de **Run** (ícone de play verde ▶️) ou pressione `Shift + F10` para rodar o aplicativo no dispositivo selecionado.

---
Feito com 💚 para um futuro mais sustentável.
