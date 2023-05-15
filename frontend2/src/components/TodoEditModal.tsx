import React, {useState} from 'react';
import {Todo} from "../model/TodoModel";
import Modal from "react-modal";
import './TodoEditModal.css';

type Props = {
    todo: Todo;
    closeModal: () => void;
    onSave: (updatedTodo: Todo) => void;
};

const TodoEditModal: React.FC<Props> = ({ todo, closeModal, onSave }) => {

    const [updatedTodo, setUpdatedTodo] = useState<Todo>({ ...todo });

    return (
        <Modal isOpen={true} onRequestClose={closeModal}>
            <div className="editModalContainer">
                <h2>TodoItem bearbeiten</h2>
                <p>ID: {todo.id}</p>

                <div>
                    <label>Description </label><input value={updatedTodo.description} onChange={(e) =>
                    setUpdatedTodo({ ...updatedTodo, description: e.target.value })} />
                </div>

                <div>
                    <label>Status </label>
                    <select id="status" value={updatedTodo.status} onChange={(e) =>
                        setUpdatedTodo({ ...updatedTodo, status: e.target.value })}>
                        <option value="OPEN">OPEN</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                        <option value="DONE">DONE</option>
                    </select>
                </div>

                <button onClick={closeModal}>Reset</button>
                <button onClick={() => onSave(updatedTodo)}>Save</button>
            </div>
        </Modal>
    );
}

export default TodoEditModal;




