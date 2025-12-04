sequenceDiagram
    participant Client
    participant Controller as BookController
    participant Service as BookService
    participant Repo as BookRepository

    Client->>Controller: PUT /api/books/{id} + BookRequest
    Controller->>Service: updateBook(id, request)
    Service->>Repo: findById(id)
    alt found
        Repo-->>Service: Book entity
        Service->>Repo: save(updated Book)
        Repo-->>Service: saved Book
        Service-->>Controller: APIResponse<String>(200, "Book updated")
        Controller-->>Client: 200 OK + APIResponse
    else not found
        Repo-->>Service: empty
        Service-->>Controller: APIResponse<String>(404, null)
        Controller-->>Client: 404 Not Found
    end

    Client->>Controller: DELETE /api/books/{id}
    Controller->>Service: deleteBook(id)
    Service->>Repo: findById(id)
    alt found
        Repo->>Service: Book entity
        Service->>Repo: delete(book)
        Repo-->>Service: ack
        Service-->>Controller: APIResponse<String>(200, "Book deleted")
        Controller-->>Client: 200 OK + APIResponse
    else not found
        Repo-->>Service: empty
        Service-->>Controller: APIResponse<String>(404, null)
        Controller-->>Client: 404 Not Found
    end
