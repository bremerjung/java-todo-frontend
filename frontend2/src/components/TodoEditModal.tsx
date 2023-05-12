import React from 'react';
import {Todo} from "../model/TodoModel";
import Modal from "react-modal";
import './TodoEditModal.css';

type Props = {
    todo: Todo;
    closeModal: () => void;
};

const TodoEditModal: React.FC<Props> = ({ todo, closeModal }) => {
    return (
        <Modal isOpen={true} onRequestClose={closeModal}>
            <div className="editModalContainer">
                <h2>TodoItem bearbeiten</h2>
                <p>ID: {todo.id}</p>

                <div>
                    <label>Description </label><input value={todo.description}/>
                </div>

                <div>
                    <label>Status </label>
                    <select id="status">
                        <option value="OPEN">OPEN</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                        <option value="DONE">DONE</option>
                    </select>
                </div>

                <button onClick={closeModal}>Reset</button>
                <button onClick={closeModal}>Save</button>
            </div>
        </Modal>
    );
}

export default TodoEditModal;




