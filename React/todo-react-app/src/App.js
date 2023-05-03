import React ,{useState, useEffect} from 'react';
import './App.css';
import Todo from './Todo';
import { Container,List, Paper } from '@mui/material';
import AddTodo from './AddTodo';
import { call } from './ApiService'

function App() {
  const [items,setItems] = useState([]);
  // useEffect(()=> {
  // const requestOptions = {
  //   method: "GET",
  //   Headers: {"Content-Type" : "application/json",}
  // };
  // fetch("http://10.125.121.222:8080/todo", requestOptions)
  //   .then((response) => response.json())
  //   .then(
  //     (response) => {
  //       setItems(response.data);
  //     },
  //     (error)=>{ 
  //     }
  //   );
  // },[]) // 각 method 별로 다 만들기 싫다.;
  useEffect(()=> {
      call("/todo", "GET",null)
      .then((response) => setItems(response.data));
  },[]);
  
  const addItem = (item) =>{
    // item.id = "ID-"+items.length //key 위한 id
    // item.done = false; // done 초기화
    // //업데이트는 setItems, 새 배열만들기
    // setItems([...items, item]);
    // console.log("items: ",items);
    call("/todo/test", "POST" ,item)
    .then((response) => setItems(response.data));
  };
  const deleteItem =(item)=> {
    // const newItems = items.filter(e => e.id !== item.id);
    // setItems([...newItems]);
    call("/todo", "DELETE", item)
    .then((response) => setItems(response.data));
  }
  const editItem = (item) => {
    call("/todo", "PUT", item)
    .then ((response)=>setItems(response.data));
  };
  
  let todoItems = items.length>0 && (
    <Paper style={{margin:16}}>
      <List>
        {items.map((item)=>(
          <Todo item = {item} key={item.id} editItem={editItem} deleteItem={deleteItem}/>
        ))}
      </List>
    </Paper>
    );
  return <div className='App'>
            <Container maxwidth="md">
              <AddTodo addItem={addItem}/>  
            <div className='TodoList'>{todoItems}</div>
            </Container>
        </div>;
}


export default App;
