import React, {useEffect, useState} from 'react';
import {Todo} from "../model/TodoModel";
import axios from "axios";
import TodoItem from "./TodoItem";
import './TodoList.css';

function TodoList() {

    const [todos, setToDos] = useState<Todo[]>([])
    const [inputValue, setInputValue] = useState<string>("")

    useEffect(() => {
        getAllTodos();
    }, [])

    function getAllTodos() {
        axios.get("/api/todo").then(response => {
            console.log(response)
            setToDos(response.data)
        })

    }

    function addTodo() {
        axios.post("/api/todo", { description: inputValue, status: "OPEN" })
            .then(() => {
                setInputValue(""); // Eingabefeld leeren
                getAllTodos();
            });
    }

    function handleInputChange(event: React.ChangeEvent<HTMLInputElement>) {
        setInputValue(event.target.value);
    }

    return (
        <div>
            <h1>My personal todo list</h1>
            <div className="todoBoard">
                <div className="todoListColumn">
                    <h2 className="boardColumnHeader">OPEN</h2>
                    {todos.filter((currentTodo:Todo) => currentTodo.status==="OPEN").map((currentTodo:Todo) =>
                        <TodoItem key={currentTodo.id} todo={currentTodo} getAll={getAllTodos}></TodoItem>)}
                </div>
                <div className="todoListColumn">
                    <h2 className="boardColumnHeader">IN PROGRESS </h2>
                    {todos.filter((currentTodo:Todo) => currentTodo.status==="IN_PROGRESS").map((currentTodo:Todo) =>
                    <TodoItem key={currentTodo.id} todo={currentTodo} getAll={getAllTodos}></TodoItem>)}
                </div>
                <div className="todoListColumn">
                    <h2 className="boardColumnHeader">DONE</h2>
                    {todos.filter((currentTodo:Todo) => currentTodo.status==="DONE").map((currentTodo:Todo) =>
                    <TodoItem key={currentTodo.id} todo={currentTodo} getAll={getAllTodos}></TodoItem>)}
                </div>
            </div>

            <div id="todoListfooter">
                <input value={inputValue} onChange={handleInputChange} />
                <button onClick={addTodo}>Add</button>
                <button onClick={getAllTodos}>Manual refresh: get all todos</button>
            </div>

        </div>
    );
}

export default TodoList;