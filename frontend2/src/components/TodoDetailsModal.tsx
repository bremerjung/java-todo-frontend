import React from 'react';
import Modal from 'react-modal';
import {Todo} from "../model/TodoModel";

type Props = {
    todo: Todo;
    closeModal: () => void;
};

const TodoDetailsModal: React.FC<Props> = ({ todo, closeModal }) => {
    return (
        <Modal isOpen={true} onRequestClose={closeModal}>
            <h2>Todo Details</h2>
            <p>ID: {todo.id}</p>
            <p>Description: {todo.description}</p>
            <p>Status: {todo.status}</p>
            <button onClick={closeModal}>Close</button>
        </Modal>
    );
};

export default TodoDetailsModal;
