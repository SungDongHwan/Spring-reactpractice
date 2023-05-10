import React from "react";
import { signin } from "./ApiService";
import { Link } from "react-router-dom";
import { Container, Grid, TextField, Typography, Button } from "@mui/material";

function Login() {
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        const username = data.get("username");
        const password = data.get("password");
        // api service 의 signin 메서드를 사용해 로그인
        signin({ "username": username, "password": password });
    };
    return (
        <Container component="main" maxWidth="xs" style={{ margin: "8%" }}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Typography component="h1" variant="h5">
                        로그인
                    </Typography>
                    <a href="http://localhost:8080/oauth2/google">
                        구글 로그인 
                    </a>
                </Grid>
            </Grid>
            <form noValidate onSubmit={handleSubmit}>
                {" "}
                {/* submit 버튼을 누르면 handle submit 이 실행 */}
                <Grid container spacing={2}>
                <Grid item xs={12}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        id="username"
                        label="아이디"
                        name="username"
                        autoComplete="username"
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        name="password"
                        id="password"
                        label="패스워드"
                        type="password"
                        autoComplete="current-password"
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary">
                        로그인
                    </Button>
                </Grid>
                <Grid item>
                    <Link to="/signup" variant="body2">
                        계정이 없습니까? 여기서 가입하세요.
                    </Link>
                </Grid>
                </Grid>
            </form>
        </Container>


    );
}

export default Login