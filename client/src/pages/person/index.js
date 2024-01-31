import React from "react";
import {Link} from 'react-router-dom';
import {FiPower, FiEdit, FiTrash2} from 'react-icons/fi';
import './styles.css';
import imageLuiz from '../../assets/homePage/luiz.png'

export default function Person(){
    return(
        <div className="person-container">
            <header>
                <img className="userImage" src={imageLuiz} alt="user_image"/>
                <span>Welcome, <strong>Luiz</strong></span>
                <Link className="button" to="/adicionar-usuario">Adicionar usuário</Link>
                <button type="button">
                    <FiPower size={18} color ="251fc5"/>
                </button>
            </header>

            <h1>Usuários registrados no sistema</h1>
            <ul>
                <li>
                    <strong>Title:</strong>
                    <p>Angelo Torres</p>
                    <strong>Cpf:</strong>
                    <p>12039810958</p>
                    <strong>Data de nascimento:</strong>
                    <p>31/09/1933</p>
                    <strong>Fortuna bruta:</strong>
                    <p>1.3 Bilhoes de dolares</p>

                    <button type="button">
                        <FiEdit size={20} color="251fc5"/>
                    </button>

                    <button type="button">
                        <FiTrash2 size={20} color="251fc5"/>
                    </button>
                </li>

                <li>
                    <strong>Title:</strong>
                    <p>Angelo Torres</p>
                    <strong>Cpf:</strong>
                    <p>12039810958</p>
                    <strong>Data de nascimento:</strong>
                    <p>31/09/1933</p>
                    <strong>Fortuna bruta:</strong>
                    <p>1.3 Bilhoes de dolares</p>

                    <button type="button">
                        <FiEdit size={20} color="251fc5"/>
                    </button>

                    <button type="button">
                        <FiTrash2 size={20} color="251fc5"/>
                    </button>
                </li>

                <li>
                    <strong>Title:</strong>
                    <p>Angelo Torres</p>
                    <strong>Cpf:</strong>
                    <p>12039810958</p>
                    <strong>Data de nascimento:</strong>
                    <p>31/09/1933</p>
                    <strong>Fortuna bruta:</strong>
                    <p>1.3 Bilhoes de dolares</p>

                    <button type="button">
                        <FiEdit size={20} color="251fc5"/>
                    </button>

                    <button type="button">
                        <FiTrash2 size={20} color="251fc5"/>
                    </button>
                </li>

                <li>
                    <strong>Title:</strong>
                    <p>Angelo Torres</p>
                    <strong>Cpf:</strong>
                    <p>12039810958</p>
                    <strong>Data de nascimento:</strong>
                    <p>31/09/1933</p>
                    <strong>Fortuna bruta:</strong>
                    <p>1.3 Bilhoes de dolares</p>

                    <button type="button">
                        <FiEdit size={20} color="251fc5"/>
                    </button>

                    <button type="button">
                        <FiTrash2 size={20} color="251fc5"/>
                    </button>
                </li>
            </ul>
        </div>
    );
}