package com.carbontower.stream

/*

swagger: "2.0"
info:
  description: "Essa é a documentação da API da Carbon Tower para uso do grupo da faculdade"
  version: "1.0.0"
  title: "Carbon Tower Stream Backend"
host: "35.247.250.80:8000"
tags:
- name: "Games"
- name: "Streams"
- name: "Users"
schemes:
- "http"

paths:
    /games/name/:name:
      get:
        tags:
        - "Games"
        summary: "Retorna todos os games da API do Twitch com esse nome"
        description: "Retorna todos os games da API do Twitch com esse nome"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Game"
    /games/id/:id:
      get:
        tags:
        - "Games"
        summary: "Retornar todos os games da API do Twitch com esse id"
        description: "Retornar todos os games da API do Twitch com esse id"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Game"
    /games/top/:quantity:
      get:
        tags:
        - "Games"
        summary: "Retornar os jogos mais populares na API do Twitch, aceita ate no máximo 100 jogos"
        description: "Retornar os jogos mais populares na API do Twitch, aceita ate no máximo 100 jogos"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/TopGames"
    /games/top:
      get:
        tags:
        - "Games"
        summary: "Retornar os 30 jogos mais populares na API do Twitch."
        description: "Retornar os 30 jogos mais populares na API do Twitch."
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/TopGames"
    /streams/:quantity:
      get:
        tags:
        - "Streams"
        summary: "Retorna as streams mais famosas, no máximo 100 streams"
        description: "Retorna as streams mais famosas, no máximo 100 streams"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Streams"
    /streams/game/:game_id:
      get:
        tags:
        - "Streams"
        summary: "Retornar as streams de um jogo especifico, especificado pelo id do jogo da API da Twitch"
        description: "Retornar as streams de um jogo especifico, especificado pelo id do jogo da API da Twitch"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Streams"
    /streams/lang/:language:
      get:
        tags:
        - "Streams"
        summary: "Retorna as streams de uma lingua especifica"
        description: "Retorna as streams de uma lingua especifica"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Streams"
    /streams/user-id/:user_id:
      get:
        tags:
        - "Streams"
        summary: "Retorna as streams de um usuário, especificado pelo id do usuário da API do Twitch ou banco de dados nosso(Table T_USER_STREAM"
        description: "Retorna as streams de um usuário, especificado pelo id do usuário da API do Twitch ou banco de dados nosso(Table T_USER_STREAM"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Streams"
    /streams/user-login/:user_login:
      get:
        tags:
        - "Streams"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/Streams"
    /streams/games/db:
      get:
        tags:
        - "Streams"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /streams/championship/:idchampionship/:idstream:
      post:
        tags:
        - "Streams"
        summary: "Logout do Usuário | Necessário Cookie"
        description: "Destroi o cookie"
        responses:
          200:
            description: OK
    /streams/by-id-user-role/:id-user-role:
      get:
        tags:
        - "Streams"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /streams/by-id-user-role-and-login/:id-user-role/:login:
      get:
        tags:
        - "Streams"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /users/login/:login:
      get:
        tags:
        - "Users"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /users/id/:id:
      get:
        tags:
        - "Users"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /users/signup/:login/:id-user-role:
      post:
        tags:
        - "Users"
        summary: "Logout do Usuário | Necessário Cookie"
        description: "Destroi o cookie"
        responses:
          200:
            description: OK
    /users/update/streams/:login:
      post:
        tags:
        - "Users"
        summary: "Logout do Usuário | Necessário Cookie"
        description: "Destroi o cookie"
        responses:
          200:
            description: OK

definitions:
  Stream:
    type: object
    properties:
      idStream:
        description: "Data da Medição"
        type: integer
      language:
        description: "Data da Medição"
        type: string
      title:
        description: "Data da Medição"
        type: string
      idUserStream_fk:
        description: "Data da Medição"
        type: string
      viewCount:
        description: "Data da Medição"
        type: integer
  StreamList:
    type: array
    items:
      type: object
      properties:
        idStream:
          description: "Data da Medição"
          type: integer
        language:
          description: "Data da Medição"
          type: string
        title:
          description: "Data da Medição"
          type: string
        idUserStream_fk:
          description: "Data da Medição"
          type: string
        viewCount:
          description: "Data da Medição"
          type: integer
  DataGame:
    type: object
    properties:
      boxArtUrl:
        description: "Placa Mãe"
        type: string
      id:
        description: "Sistema Operacional"
        type: string
      name:
        description: "Fabricante"
        type: string
  DataStream:
    type: object
    properties:
      communityIds:
        description: "Razão Social do cliente"
        type: array
        items:
          type: string
      gameId:
        description: "Razão Social do cliente"
        type: string
      id:
        description: "Razão Social do cliente"
        type: integer
      language:
        description: "Razão Social do cliente"
        type: string
      startedAt:
        description: "Razão Social do cliente"
        type: string
      thumbnailUrl:
        description: "Razão Social do cliente"
        type: string
      title:
        description: "Razão Social do cliente"
        type: string
      type:
        description: "Razão Social do cliente"
        type: string
      userId:
        description: "Razão Social do cliente"
        type: string
      userName:
        description: "Razão Social do cliente"
        type: string
      viewerCount:
        description: "Razão Social do cliente"
        type: integer
  DataUser:
    type: object
    properties:
      bradcasterType:
        description: "Razão Social do cliente"
        type: string
      description:
        description: "Razão Social do cliente"
        type: string
      displayName:
        description: "Razão Social do cliente"
        type: string
      email:
        description: "Razão Social do cliente"
        type: string
      id:
        description: "Razão Social do cliente"
        type: integer
      login:
        description: "Razão Social do cliente"
        type: string
      offlineImageUrl:
        description: "Razão Social do cliente"
        type: string
      type:
        description: "Razão Social do cliente"
        type: string
      viewCount:
        description: "Razão Social do cliente"
        type: string
  Game:
    type: object
    properties:
      data:
        description: "Razão Social do cliente"
        type: array
        items:
          type: object
          properties:
            boxArtUrl:
              description: "Placa Mãe"
              type: string
            id:
              description: "Sistema Operacional"
              type: string
            name:
              description: "Fabricante"
              type: string
  TopGames:
    type: object
    properties:
      data:
        description: "Razão Social do cliente"
        type: array
        items:
          type: object
          properties:
            boxArtUrl:
              description: "Placa Mãe"
              type: string
            id:
              description: "Sistema Operacional"
              type: string
            name:
              description: "Fabricante"
              type: string
  Streams:
    type: object
    properties:
      data:
        description: "Razão Social do cliente"
        type: array
        items:
          type: object
          properties:
            communityIds:
              description: "Razão Social do cliente"
              type: array
              items:
                type: string
            gameId:
              description: "Razão Social do cliente"
              type: string
            id:
              description: "Razão Social do cliente"
              type: integer
            language:
              description: "Razão Social do cliente"
              type: string
            startedAt:
              description: "Razão Social do cliente"
              type: string
            thumbnailUrl:
              description: "Razão Social do cliente"
              type: string
            title:
              description: "Razão Social do cliente"
              type: string
            type:
              description: "Razão Social do cliente"
              type: string
            userId:
              description: "Razão Social do cliente"
              type: string
            userName:
              description: "Razão Social do cliente"
              type: string
            viewerCount:
              description: "Razão Social do cliente"
              type: integer
  Users:
    type: object
    properties:
      data:
        description: "Razão Social do cliente"
        type: array
        items:
          type: object
          properties:
            boxArtUrl:
              description: "Placa Mãe"
              type: string
            id:
              description: "Sistema Operacional"
              type: string
            name:
              description: "Fabricante"
              type: string
*/