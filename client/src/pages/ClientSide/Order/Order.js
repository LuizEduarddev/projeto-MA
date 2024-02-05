import React, {useState} from "react"
import api from "../../../services/api";

export default function Order(){
    
    const [quantidade, setQuantidade ] = useState(0);
    const [nomeProduto, setNomeProduto] = useState('');

    async function handleClickMore (e){
        e.preventDefault(e);
        setQuantidade(quantidade + 1);
    }

    async function handleClickLess (e){
        e.preventDefault(e);
        setQuantidade(quantidade - 1);
    }

    async function sendOrder(e) {
        e.preventDefault();
        
        try
        {
            const responseProduto = await api.post('api/produto/get-by-name', nomeProduto);
        }
        catch(error)
        {
            alert('Uncatched error: ' + '\n' + error);
            console.log('error trying to get product id.', error);
        }
}

    return (
        <div>
            <form onSubmit={sendOrder}>
                <input 
                    placeholder="Digite o nome do prato" 
                    value={nomeProduto}
                    onChange={e => setNomeProduto(e.target.value)}/>
                <br></br>
                <button onClick={handleClickLess}>-</button>
                <h4>{quantidade}</h4>
                <button onClick={handleClickMore}>+</button>
                <button type="submit">Enviar pedido</button>
            </form>
        </div>
    )
}