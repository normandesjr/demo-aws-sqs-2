resource "aws_sqs_queue" "first_queue_fifo" {
    name                        = "hibicode-first-queue.fifo"
    fifo_queue                  = true
    
    visibility_timeout_seconds  = 30 # tempo que o consumidor tem pra deletar a mensagem, caso contrario ela volta pra fila
    receive_wait_time_seconds   = 20 # long polling

    redrive_policy = jsonencode({
        deadLetterTargetArn = aws_sqs_queue.first_queue_fifo_dlq.arn
        maxReceiveCount     = 3
    })
  
}


resource "aws_sqs_queue" "first_queue_fifo_dlq" {
    name                        = "hibicode-first-queue-dlq.fifo"
    fifo_queue                  = true
    
    visibility_timeout_seconds  = 30
    receive_wait_time_seconds   = 20
}


resource "aws_sqs_queue" "second_queue_fifo" {
    name                        = "hibicode-second-queue"
    fifo_queue                  = false

    visibility_timeout_seconds  = 30
    receive_wait_time_seconds   = 20
//    content_based_deduplication = true

}
