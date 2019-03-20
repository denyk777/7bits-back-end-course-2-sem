/*
package it.sevenbits.spring.web.controllers;
Contains post and get request
paths:
  /tasks:
    get:
      tags:
      - Tasks
      summary:  Returns all tasks from server
      operationId: getAllTask
      produces:
      - application/json
      responses:
        200:
          description: all tasks returned
          schema:
            type: array
            items:
              $ref: '#/definitions/Task'
    post:
      tags:
      - Tasks
      summary: Add task to the repository
      operationId: addTask
      consumes:
      - application/json
      produces:
      - application/json
      parameters:
      - in: body
        name: task
        description: Task to add
        schema:
          $ref: '#/definitions/AddTaskRequest'
      responses:
        201:
          description: task created
        400:
          description: invalid input, object invalid

 */