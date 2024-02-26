import { useParams, useNavigate , Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import api from "../../../../services/api";

export default function ItensPedidos()
{
    const { id } = useParams();
    const [itensPedidos, setItensPedidos] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        async function getItens()
        {
            api.post('http://localhost:8080/api/pedido/item-pedido/get-by-id-pedido/' + id)
            .then(response => {
                if (response)
                {
                    setItensPedidos(response.data)
                }
            })
            .catch(error => {
                const message = error.response.data.message;
                if (message)
                {
                    alert(message)
                }
                else{
                    alert('Erro de requisicao ou de URL.' + "\n" + "ERROR: " + error);
                }
            })
        }

        getItens();
    }, [itensPedidos])

    function errorPage()
    {
        navigate("/error/page");
    }

    return (
        <div>
            {itensPedidos && itensPedidos.length > 0 ? (
                <div>     
                        <h1>Pedido {id}.</h1>
                        <ul>
                        {itensPedidos.map(itens => (
                                <li key={itens.nomeProduto}>
                                    <h3>Produto: <strong>{itens.nomeProduto}</strong></h3>
                                    <h3>Quantidade: <strong>{itens.quantidadeProduto}</strong></h3>
                                    <hr/>
                                </li>
                            ))}
                        </ul>
                    </div>
                ) : (
                    <div>
                        {errorPage()}
                    </div>
                )
            }
        </div>
    );
}