import { signup } from "./ApiService";
import { Link } from "react-router-dom";
import React from "react";
import { Container, Grid, Typography, TextField, Button } from "@mui/material";

function SignUp() {
    const handleSubmit = (event) => {
        event.preventDefault();
        // 오브젝트의 form 을 map으로 타입캐스팅
        const data = new FormData(event.target);
        const username = data.get("username");
        const password = data.get("password");
        signup({ "username": username, "password": password }).then(
            (response) => {
                // login 페이지로 리다이렉트
                window.location.href = '/login';
            }
        );
    };

return (
    <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
        <form noValidate onSubmit={handleSubmit}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Typography component="h1" variant="h5">
                        계정 생성
                    </Typography>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        autoComplete="fname"
                        name="username"
                        variant="outlined"
                        required
                        fullWidth
                        id="username"
                        label="아이디"
                        autoFocus />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        name="password"
                        variant="outlined"
                        autoComplete="current-password"
                        required
                        fullWidth
                        id="password"
                        type="password"
                        label="password"
                        />
                </Grid>
                <Grid item xs={12}>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary">
                        계정생성
                    </Button>
                </Grid>
            </Grid>
            <Grid container justify="flex-end">
                <Grid item>
                    <Link to="/login" variant="body2">
                        이미계정이 있습니까? 로그인하세요.
                    </Link>
                </Grid>
            </Grid>
        </form>
    </Container>
)}
export default SignUp;