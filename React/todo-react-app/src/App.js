import React, { useState, useEffect } from 'react';
import './App.css';
import Todo from './Todo';
import { AppBar, Button, Container, Grid, List, Paper, Toolbar, Typography } from '@mui/material';
import AddTodo from './AddTodo';
import { call, signout } from './ApiService'

function App() {
  // navigation bar 추가
  let navigationBar = (
    <AppBar position='static'>
      <Toolbar>
        <Grid justifyContent={"space-between"} container>
          <Grid item>
            <Typography variant='h6'> 오늘의 할 일 </Typography>
          </Grid>
          <Grid item>
            <Button color='inherit' sx={{ bgcolor: 'gray' }} raised='true' onClick={signout} >
              로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  )
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
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
  useEffect(() => {
    call("/todo", "GET", null)
      .then((response) => {
        setItems(response.data)
        setLoading(false)
      });
  }, []);

  const addItem = (item) => {
    // item.id = "ID-"+items.length //key 위한 id
    // item.done = false; // done 초기화
    // //업데이트는 setItems, 새 배열만들기
    // setItems([...items, item]);
    // console.log("items: ",items);
    call("/todo/test", "POST", item)
      .then((response) => setItems(response.data));
  };
  const deleteItem = (item) => {
    // const newItems = items.filter(e => e.id !== item.id);
    // setItems([...newItems]);
    call("/todo", "DELETE", item)
      .then((response) => setItems(response.data));
  }
  const editItem = (item) => {
    call("/todo", "PUT", item)
      .then((response) => setItems(response.data));
  };

  let todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map((item) => (
          <Todo item={item} key={item.id} editItem={editItem} deleteItem={deleteItem} />
        ))}
      </List>
    </Paper>
  );

  let todoListPage = (
    <div>
      {navigationBar}
      <Container maxwidth="md">
        <AddTodo addItem={addItem} />
        <div className='TodoList'>{todoItems}</div>
      </Container>
    </div>
  );
  let loadingPage = <h1> 로딩중..</h1>;
  let content = loadingPage;
  if (!loading) {
    content = todoListPage;
  }
  return <div className='App'>
    {content}
  </div>

}

export default App;
