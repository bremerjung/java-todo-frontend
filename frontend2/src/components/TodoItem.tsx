import React, {useState} from 'react';
import {Todo} from "../model/TodoModel";
import axios from "axios";
import TodoDetailsModal from "./TodoDetailsModal";
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './TodoItem.css'
import TodoEditModal from "./TodoEditModal";

type Props = {
    todo:Todo
    getAll:() => void;
}

function TodoItem(props:Props) {

    const [isDetailsModalOpen, setIsDetailsModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);

    const openDetailsModal = () => {
        setIsDetailsModalOpen(true);
    };

    const closeDetailsModal = () => {
        setIsDetailsModalOpen(false);
    };

    const openEditModal = () => {
        setIsEditModalOpen(true);
    };

    const closeEditModal = () => {
        setIsEditModalOpen(false);
    };

    function deleteTodo() {
        axios.delete("/api/todo/" + props.todo.id).then(props.getAll)
    }

    function advanceTodo() {
        const updatedStatus = props.todo.status==="OPEN" ? "IN_PROGRESS" : "DONE";
        axios.put("/api/todo/" + props.todo.id, {description:props.todo.description, status:updatedStatus}).then(props.getAll)
        // props.todo.status="DONE"; // TODO is api call necessary here? or can it be sufficient to update frontend object?
    }

    function reopenTodo() {
        axios.put("/api/todo/" + props.todo.id, {description:props.todo.description, status:"OPEN"}).then(props.getAll)
    }

    function editTodo() {
        openEditModal();
        //axios.put("/api/todo/" + props.todo.id, {description:props.todo.description, status:"OPEN"}).then(props.getAll)
    }

    return (
        <div className="todoItem">
            <h6>#{props.todo.id}</h6>
            <h3>{props.todo.description}</h3>
            <h5>{props.todo.status}</h5>

            <div className="actionButtonContainer">
                <div className="todoActionButton" onClick={openDetailsModal}><FontAwesomeIcon icon={faCircleInfo} /></div>
                <div className="todoActionButton" onClick={editTodo}><FontAwesomeIcon icon={faPenToSquare} /></div>

                {props.todo.status != "DONE" ? <div className="todoActionButton" onClick={advanceTodo}><FontAwesomeIcon icon={faArrowRight} /></div> : <></>}

                {props.todo.status==="DONE" ? <div className="todoActionButton" onClick={deleteTodo}><FontAwesomeIcon icon={faTrash} /></div> : <></>}
            </div>

            {props.todo.status != "OPEN" ? <button id="reopenButton" onClick={reopenTodo}>Reopen</button> : <></>}


            {isDetailsModalOpen && (
                <TodoDetailsModal todo={props.todo} closeModal={closeDetailsModal} />
            )}

            {isEditModalOpen && (
                <TodoEditModal todo={props.todo} closeModal={closeEditModal} />
            )}
        </div>
    );
}

export default TodoItem;