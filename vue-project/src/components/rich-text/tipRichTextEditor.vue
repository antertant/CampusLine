<template>
  <b-card class="editor">
    <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
      <div class="menubar">

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.bold()"
          @click="commands.bold"
          title="bold"
        >
          <b-icon icon="type-bold" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.italic()"
          @click="commands.italic"
          title="italic"
        >
          <b-icon icon="type-italic" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.strike()"
          @click="commands.strike"
          title="strike"
        >
          <b-icon icon="type-strikethrough" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.underline()"
          @click="commands.underline"
          title="underline"
        >
          <b-icon icon="type-underline" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.code()"
          @click="commands.code"
          title="inline-code"
        >
          <b-icon icon="code" />
        </b-button>

<!--        <b-button-->
<!--          class="menubar__button"-->
<!--          variant="light"-->
<!--          :pressed="isActive.paragraph()"-->
<!--          @click="commands.paragraph"-->
<!--        >-->
<!--          <b-icon icon="paragraph" />-->
<!--        </b-button>-->

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.heading({ level: 1 })"
          @click="commands.heading({ level: 1 })"
        >
          <b-icon icon="type-h1" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.heading({ level: 2 })"
          @click="commands.heading({ level: 2 })"
        >
          <b-icon icon="type-h2" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.heading({ level: 3 })"
          @click="commands.heading({ level: 3 })"
        >
          <b-icon icon="type-h3" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.bullet_list()"
          @click="commands.bullet_list"
          title="unordered-list"
        >
          <b-icon icon="list-ul" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.ordered_list()"
          @click="commands.ordered_list"
          title="ordered-list"
        >
          <b-icon icon="list-ol" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.blockquote()"
          @click="commands.blockquote"
          title="quote"
        >
          <b-icon icon="chat-left-quote" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          :pressed="isActive.code_block()"
          @click="commands.code_block"
          title="code-block"
        >
          <b-icon icon="code-square" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          @click="commands.horizontal_rule"
          title="horizontal-rule"
        >
          <b-icon icon="hr" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          @click="commands.undo"
          title="undo"
        >
          <b-icon icon="arrow90deg-left" />
        </b-button>

        <b-button
          class="menubar__button"
          variant="light"
          @click="commands.redo"
          title="redo"
        >
          <b-icon icon="arrow90deg-right" />
        </b-button>

      </div>
    </editor-menu-bar>

    <editor-content class="editor__content m-3" :editor="editor" />
  </b-card>
</template>

<script>
import {Editor, EditorContent, EditorMenuBar} from 'tiptap'
import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History,
} from 'tiptap-extensions'

export default {
  name: "tipRichTextEditor",
  components: {
    EditorContent,
    EditorMenuBar,
  },
  data() {
    return {
      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History(),
        ],
        content: `
          <h2>
            Hi there,
          </h2>
          <p>
            this is a very <em>basic</em> example of tiptap.
          </p>
          <pre><code>body { display: none; }</code></pre>
          <ul>
            <li>
              A regular list
            </li>
            <li>
              With regular items
            </li>
          </ul>
          <blockquote>
            It's amazing 👏
            <br />
            – mom
          </blockquote>
        `,
      }),
    }
  },
  beforeDestroy() {
    this.editor.destroy()
  },
}
</script>

<style scoped>

</style>
