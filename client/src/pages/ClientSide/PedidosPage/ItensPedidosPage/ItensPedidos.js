import { useParams, useNavigate , Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import api from "../../../../services/api";
import { MdOutlinePayments as Pay} from "react-icons/md";

export default function ItensPedidos()
{
    const { id } = useParams();
    const [itensPedidos, setItensPedidos] = useState([]);
    const [totalConta, setTotalConta] = useState(0);
    const navigate = useNavigate();
    const dinheiro = 1000;

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
                    alert(message);
                }
                else{
                    alert('Erro de requisicao ou de URL.' + "\n" + "ERROR: " + error);
                }
            })
        }
        
        async function getTotal()
        {
            api.post('http://localhost:8080/api/pedido/get-by-id/' + id)
            .then(responseTotalConta => {
                const total = responseTotalConta.data.totalPedido;
                if (total)
                {
                    setTotalConta(total);
                }
                else{
                    navigate('/cliente/pedidos/itens/' + id);
                }
            })
            .catch(error => {
                const message = error.response.data.message;
                if (message)
                {
                    alert(message);
                }
                else{
                    alert('Erro de requisicao ou de URL.' + "\n" + "ERROR: " + error);
                }
            })
        }

        getTotal();
        getItens();
    }, [itensPedidos])

    function errorPage()
    {
        navigate("/error/page");
    }

    async function efetuarPagamento()
    {
        if (totalConta)
        {
            if (dinheiro >= totalConta)
            {
                const pedido = {
                    "pedidoPago": true
                }

                api.put('http://localhost:8080/api/pedido/alter/' + id, pedido)
                .then(response => {
                    if (response && response.data) {
                        alert('pedido pago com sucesso!');
                        navigate('/cliente/pedidos');
                    } else {
                        alert('Resposta inválida da API ao tentar efetuar o pagamento.');
                    }
                })
                .catch(error => {
                    const message = error.response ? error.response.data.message : 'Erro desconhecido ao tentar efetuar o pagamento.';
                    alert(message);
                });
            }
        }
        else{
            alert('Erro ao tentar efetuar o pagamento.');
        }
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
                                    <h3>Valor: R${itens.precoProduto}</h3>
                                    <hr/>
                                </li>
                            ))}
                        </ul>
                        <h3>Total do pedido: R${totalConta}</h3>
                        <button onClick={() => efetuarPagamento()}>
                            <h3>
                                <Pay/>
                                | Efetuar pagamento
                            </h3>
                        </button>
                    </div>
                ) : (
                    <div>
                        Esta é sua página de pedidos, assim que um for feito aparecerá aqui!
                    </div>
                )
            }
        </div>
    );
}