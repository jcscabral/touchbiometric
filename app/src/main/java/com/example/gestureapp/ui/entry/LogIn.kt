package com.example.gestureapp.ui.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestureapp.R
import com.example.gestureapp.data.DataSource


@Composable
fun LogIn(
    id: Int,
    userName: String,
    isPasswordWrong: Boolean,
    useOption: String,
    onTrainClicked:(String)->Unit,
    onLoginClick: (String) -> Unit,
    useOptionList: List<String> = DataSource.useOption,
)
{
    var passwordText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Acesso cliente n°${id}",
            style = MaterialTheme.typography.titleLarge,
        )
        if (!isPasswordWrong){
            Text(
                text = ""
            )
        }
        else{
            Text(
                text =  "Senha errada, favor tente novamente"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = { },
                label = { Text(text = "Usuário") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                singleLine = true
            )
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText =  it
                },
                label = {
                    if(isPasswordWrong){
                        Text(text = "Tente novamente")
                        //TODO show visually w modifier
                    }
                    else{
                        Text(text = "Senha")
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done

                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            for (use in useOptionList){
                Row(
                    modifier = Modifier.selectable(
                        selected = useOption == use ,
                        onClick = {
                            onTrainClicked(use)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected = useOption == use,
                        onClick = {
                            onTrainClicked(use)
                        }
                    )
                    Text(use)
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onLoginClick(passwordText)
                }
            ) {
                Text(
                    text = stringResource(R.string.button_entrar),
                    fontSize = 16.sp
                )
            }


        }
    }
}
